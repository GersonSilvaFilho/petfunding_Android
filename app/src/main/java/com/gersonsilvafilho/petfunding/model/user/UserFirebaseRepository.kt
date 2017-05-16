package com.gersonsilvafilho.petfunding.model.user

import android.os.Bundle
import android.util.Log
import com.facebook.AccessToken
import com.facebook.GraphRequest
import com.facebook.login.LoginManager
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import durdinapps.rxfirebase2.RxFirebaseAuth
import durdinapps.rxfirebase2.RxFirebaseDatabase
import io.reactivex.Completable
import io.reactivex.Observable
import org.json.JSONException
import java.util.*


/**
 * Created by GersonSilva on 5/8/17.
 */
class UserFirebaseRepository : UserRepository
{


    val database = FirebaseDatabase.getInstance()
    var usersRef = database.getReference("users")
    var mCurrentUser = User()

    private val  mAuth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun userStatus(): Observable<Boolean> {
        return RxFirebaseAuth.observeAuthState(mAuth)
                .map { t -> t.currentUser != null }
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
        return mCurrentUser
    }

    override fun addMatch(petId:String): Observable<String>? {
        val key = usersRef.child(getCurrentUserId()).child("matchs").child(petId)
        var match = Match()
        val newMap = HashMap(mCurrentUser.matchs)
        newMap.put(key.key, match!!)
        mCurrentUser.matchs = newMap
        return RxFirebaseDatabase.updateChildren(key,match.toMap()).subscribe { match}
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

                Log.d("Facebook Parameters", "Passou =" + name)
                val key = usersRef.child(getCurrentUserId())
                mCurrentUser.username = name
                mCurrentUser.uid = getCurrentUserId()
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

}
