package com.gersonsilvafilho.petfunding.model.match

import com.gersonsilvafilho.petfunding.model.user.Match
import com.google.firebase.database.FirebaseDatabase
import durdinapps.rxfirebase2.DataSnapshotMapper
import durdinapps.rxfirebase2.RxFirebaseDatabase
import io.reactivex.Single

/**
 * Created by GersonSilva on 6/4/17.
 */
class MatchFirebaseRepository : MatchReposity{


    val database = FirebaseDatabase.getInstance()
    var matchesRef = database.getReference("matches")

    override fun addMatch(petId: String, userId:String): Single<String> {
        val key = matchesRef.push()
        var match = Match()
        match.petId = petId
        match.userId = userId
        match.uid = key.key
        return RxFirebaseDatabase.updateChildren(key, match.toMap()).toSingle { key.key }
    }

    override fun checkIfMatchExists(petId: String, userId: String): Single<Boolean> {
        val ref = matchesRef.orderByChild("userId").equalTo(userId)
        return RxFirebaseDatabase.observeSingleValueEvent(ref, DataSnapshotMapper.listOf(Match::class.java))
                                    .map { t -> t.map { match -> match.petId }.contains(petId) }.toSingle()
    }

    override fun getAllMatches(userId:String): Single<List<Match>> {
        val ref = matchesRef.orderByChild("userId").equalTo(userId)
        return RxFirebaseDatabase.observeSingleValueEvent(ref, DataSnapshotMapper.listOf(Match::class.java)).toSingle()
    }

    override fun getMatch(petId: String, userId: String): Single<Match> {
        val ref = matchesRef.orderByChild("userId").equalTo(userId)
        return RxFirebaseDatabase.observeSingleValueEvent(ref, DataSnapshotMapper.listOf(Match::class.java))
                .map { t -> t.filter { match -> match.petId ==  petId}.first() }
                .toSingle()
    }

    override fun getAllMatchesFromPet(petId:String): Single<List<Match>> {
        val ref = matchesRef.orderByChild("petId").equalTo(petId)
        return RxFirebaseDatabase.observeSingleValueEvent(ref, DataSnapshotMapper.listOf(Match::class.java)).toSingle()
    }

    override fun addChatToMatch(match:Match, chatId:String)
    {
        val key = matchesRef.child(match.uid)
        match.chatId = chatId
        RxFirebaseDatabase.updateChildren(key, match.toMap()).subscribe { }
    }
}