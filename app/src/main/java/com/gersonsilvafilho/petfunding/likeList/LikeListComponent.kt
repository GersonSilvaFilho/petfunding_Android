package com.gersonsilvafilho.petfunding.likeList

import com.gersonsilvafilho.petfunding.util.ActivityScope
import dagger.Subcomponent

/**
 * Created by GersonSilva on 5/22/17.
 */
@ActivityScope
@Subcomponent(modules = arrayOf(LikeListModule::class))
interface LikeListComponent {
    fun inject(activity: LikeListActivity)
}