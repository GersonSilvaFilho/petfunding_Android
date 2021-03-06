package com.gersonsilvafilho.petfunding.model.user

import android.os.Bundle
import android.util.Log
import com.facebook.AccessToken
import com.facebook.GraphRequest
import com.facebook.login.LoginManager
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import durdinapps.rxfirebase2.DataSnapshotMapper
import durdinapps.rxfirebase2.RxFirebaseAuth
import durdinapps.rxfirebase2.RxFirebaseDatabase
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

import org.json.JSONException


/**
 * Created by GersonSilva on 5/8/17.
 */
class UserFirebaseRepository : UserRepository
{

    private val database = FirebaseDatabase.getInstance()
    private var usersRef = database.getReference("users")
    private var mCurrentUser: User = User()

    private val userIsLoggedSubject = BehaviorSubject.create<Boolean>()

    private val  mAuth: FirebaseAuth = FirebaseAuth.getInstance()

    init {
        RxFirebaseAuth.observeAuthState(mAuth)
            .map { t -> t.currentUser != null }
            .subscribe(userIsLoggedSubject)
    }


    override fun isUserLoggedIn(): Observable<Boolean> {
        return userIsLoggedSubject
    }

    override fun loginWithFacebook(token: String): Observable<Boolean> {
        val credential = FacebookAuthProvider.getCredential(token)
        RxFirebaseAuth.signInWithCredential((mAuth), credential)
                .map { authResult -> authResult.user!= null }
                .toObservable()
            .doOnNext { getUsernameFromFacebook() }
            .subscribe(userIsLoggedSubject)

        return userIsLoggedSubject
    }

    override fun userLogout() {
        userIsLoggedSubject.onNext(false)
        LoginManager.getInstance().logOut()
        mAuth.signOut()
    }

    override fun getCurrentUserId(): String? {
        return FirebaseAuth.getInstance().currentUser?.uid
    }

    override fun getCurrentUser(): User {
        return mCurrentUser
    }

    override fun getUsernameFromFacebook()
    {
        Log.d("Facebook Parameters", "GetNameFrom FB")
        val request = GraphRequest.newMeRequest(
                AccessToken.getCurrentAccessToken()
        ) { fbObject, _ ->
            // Application code
            try {

                val email = fbObject.getString("email")
                val gender = fbObject.getString("gender")
                val name = fbObject.getString("name")
                val id = fbObject.getString("id")


                Log.d("Facebook Parameters", "Passou =" + name)
                val key = usersRef.child(getCurrentUserId()!!)
                mCurrentUser.username = name
                mCurrentUser.uid = getCurrentUserId()!!
                mCurrentUser.email = email
                mCurrentUser.gender = gender
                mCurrentUser.imageUrl = "https://graph.facebook.com/"+id+"/picture?type=large"

                RxFirebaseDatabase.updateChildren(key,mCurrentUser.toMap()).subscribe()

            } catch (e: JSONException) {
                Log.d("Facebook Parameters", "Merda")
                e.printStackTrace()
            }
        }
        val parameters = Bundle()
        parameters.putString("fields", "id,name,email,gender,birthday")
        request.parameters = parameters
        request.executeAsync()
    }

    override fun addUnmatch(petId: String): Completable {
        getCurrentUserId()?.let {
            val key = usersRef.child(it)
            mCurrentUser.unmatches.add(petId)
            return RxFirebaseDatabase.updateChildren(key, mCurrentUser.toMap())
        }
        return Completable.complete()
    }

    override fun getUserFromMatch(matchId:String):Observable<List<User>>
    {
        val ref = usersRef
        return RxFirebaseDatabase.observeSingleValueEvent(ref, DataSnapshotMapper.listOf(User::class.java))
                .toObservable()
    }

    fun getAllMyChatIds()
    {

    }

    override fun addMatchToUser(matchId: String): Completable {
        getCurrentUserId()?.let {
            val key = usersRef.child(it)
            mCurrentUser.matches.add(matchId)
            return RxFirebaseDatabase.updateChildren(key, mCurrentUser.toMap())
        }
        return Completable.complete()
    }

}