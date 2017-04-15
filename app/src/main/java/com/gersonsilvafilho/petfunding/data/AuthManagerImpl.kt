


import com.gersonsilvafilho.petfunding.data.AuthManager
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.GoogleApiClient
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import rx.Subscriber

class AuthManagerImpl(private val facebookApiClient: GoogleApiClient) : AuthManager {

    private val auth: FirebaseAuth

    init {
        this.auth = FirebaseAuth.getInstance()
    }

    override fun signInGoogle(acct: GoogleSignInAccount, subscriber: Subscriber<String>) {
        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        auth.signInWithCredential(credential)
                .addOnCompleteListener { task ->
                    if (!subscriber.isUnsubscribed) {
                        if (task.isSuccessful) {
                            saveUser(task.result.user, subscriber)
                        } else {
                            subscriber.onError(FirebaseException(task.exception!!.message!!))
                        }
                    }
                }
    }

    fun signInFacebook(acct: GoogleSignInAccount, subscriber: Subscriber<String>) {
        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        auth.signInWithCredential(credential)
                .addOnCompleteListener { task ->
                    if (!subscriber.isUnsubscribed) {
                        if (task.isSuccessful) {
                            saveUser(task.result.user, subscriber)
                        } else {
                            subscriber.onError(FirebaseException(task.exception!!.message!!))
                        }
                    }
                }
    }


    override fun signOut(subscriber: Subscriber<String>) {
        val id = getCurrentUserId()
        auth.signOut()
//        googleApiClient.registerConnectionCallbacks(object : GoogleApiClient.ConnectionCallbacks {
//            override fun onConnected(bundle: Bundle?) {
//                Auth.GoogleSignInApi.revokeAccess(googleApiClient).setResultCallback(
//                        { status ->
//                            googleApiClient.disconnect()
//                            googleApiClient.unregisterConnectionCallbacks(this)
//                            if (!subscriber.isUnsubscribed) {
//                                if (status.isSuccess()) {
//                                    deleteCache()
//                                    subscriber.onNext(id)
//                                } else {
//                                    subscriber.onError(AuthException())
//                                }
//                            }
//                        })
//            }
//
//            override fun onConnectionSuspended(i: Int) {}
//        })
//        googleApiClient.connect()
    }

    override fun isSignedIn(): Boolean {
        return auth.currentUser != null
    }

    override fun getCurrentUserId(): String {
        var id: String? = null
        if (isSignedIn()) {
            id = auth.currentUser!!.uid
        }
        return id!!
    }

    private fun saveUser(user: FirebaseUser, subscriber: Subscriber<String>) {
//        val userDto = UserDto()
//        userDto.setId(user.uid)
//        if (!TextUtils.isEmpty(user.displayName)) {
//            val name = user.displayName!!.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
//            userDto.setFirstName(name[0])
//            userDto.setLastName(name[1])
//        }

        //createUser.execute(userDto, subscriber)
    }
}