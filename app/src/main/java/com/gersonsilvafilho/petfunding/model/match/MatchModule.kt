package com.gersonsilvafilho.petfunding.model.pet

import com.gersonsilvafilho.petfunding.model.match.MatchFirebaseRepository
import com.gersonsilvafilho.petfunding.model.match.MatchReposity
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by GersonSilva on 5/20/17.
 */
@Module
class MatchModule{

    @Provides
    fun module(): MatchReposity
    {
        return MatchFirebaseRepository()
    }

}