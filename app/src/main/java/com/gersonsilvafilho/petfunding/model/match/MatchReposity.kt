package com.gersonsilvafilho.petfunding.model.match

import com.gersonsilvafilho.petfunding.model.user.Match
import io.reactivex.Single

/**
 * Created by GersonSilva on 6/3/17.
 */
interface MatchReposity {
    fun addMatch(petId: String, userId: String?): Single<String>
    fun checkIfMatchExists(petId: String, userId: String?): Single<Boolean>
    fun getAllMatches(userId: String): Single<List<Match>>
    fun getMatch(petId: String, userId: String): Single<Match>
    fun getAllMatchesFromPet(petId: String): Single<List<Match>>
    fun addChatToMatch(match: Match, chatId: String)
}