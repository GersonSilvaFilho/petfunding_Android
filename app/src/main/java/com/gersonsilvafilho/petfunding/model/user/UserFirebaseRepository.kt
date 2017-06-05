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
import org.json.JSONException


/**
 * Created by GersonSilva on 5/8/17.
 */
class UserFirebaseRepository : UserRepository
{

    val database = FirebaseDatabase.getInstance()
    var usersRef = database.getReference("users")
    var mCurrentUser : User = User()

    private val  mAuth: FirebaseAuth = FirebaseAuth.getInstance()


    override fun userStatus(): Observable<Boolean> {
        return RxFirebaseAuth.observeAuthState(mAuth)
                .map { t -> t.currentUser != null }
    }

    override fun monitorCurrentUser() {
        val key = usersRef.child(getCurrentUserId())
        RxFirebaseDatabase.observeValueEvent(key, User::class.java)
                .doOnError { e -> Log.d("UserRepo", "User is null - " + e.localizedMessage)
                     Exception("TADA !")}
                .subscribe { if(it != null) mCurrentUser = it }
    }

    override fun currentUserChanged(): Observable<User> {
        val key = usersRef.child(getCurrentUserId())
        return RxFirebaseDatabase.observeValueEvent(key, User::class.java)
                .doOnError { e -> Log.d("UserRepo", "User is null - " + e.localizedMessage)
                     Exception("TADA !")}.toObservable()
    }

    override fun loginWithFacebook(token: String): Observable<Boolean> {
        val credential = FacebookAuthProvider.getCredential(token)
        return RxFirebaseAuth.signInWithCredential((mAuth), credential)
                .map { authResult -> authResult.user!= null }
                .toObservable()
    }

    override fun userLogout() {
        LoginManager.getInstance().logOut()
        mAuth.signOut()
    }

    override fun getCurrentUserId(): String {
        return FirebaseAuth.getInstance().currentUser!!.uid
    }

    override fun getCurrentUser(): User {
        return mCurrentUser!!
    }

    override fun getUsernameFromFacebook()
    {
        Log.d("Facebook Parameters", "GetNameFrom FB")
        val request = GraphRequest.newMeRequest(
                AccessToken.getCurrentAccessToken()
        ) { `object`, response ->
            // Application code
            try {

                val email = `object`.getString("email")
                val gender = `object`.getString("gender")
                val name = `object`.getString("name")
                val id = `object`.getString("id")


                Log.d("Facebook Parameters", "Passou =" + name)
                val key = usersRef.child(getCurrentUserId())
                mCurrentUser.username = name
                mCurrentUser.uid = getCurrentUserId()
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
        val key = usersRef.child(getCurrentUserId())
        mCurrentUser.unmatches.add(petId)
        return RxFirebaseDatabase.updateChildren(key, mCurrentUser.toMap())
    }

    fun getChatListFromMyPets(petId:String)
    {
        val ref = usersRef.child("matches").orderByChild("petId").equalTo(petId)
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
        val key = usersRef.child(getCurrentUserId())
        mCurrentUser.matches.add(matchId)
        return RxFirebaseDatabase.updateChildren(key, mCurrentUser.toMap())
    }

}