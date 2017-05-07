
package com.gersonsilvafilho.petfunding.add_pet

import com.nex3z.togglebuttongroup.button.LabelToggle
import io.reactivex.Observable

/**
 * Created by GersonSilva on 4/8/17.
 */

fun com.nex3z.togglebuttongroup.SingleSelectToggleGroup.OnCheckedChangeListener() : Observable<CharSequence> {
    return Observable.defer<CharSequence> {
        Observable.create {
            if (!it.isDisposed) {
                setOnCheckedChangeListener { group, checkedId ->  it.onNext((group.findViewById(checkedId) as LabelToggle).text)}
            }
        }}
}

fun com.nex3z.togglebuttongroup.MultiSelectToggleGroup.OnCheckedStateChangeListener() : Observable<List<String>> {
    return Observable.defer<List<String>> {
        Observable.create {
            if (!it.isDisposed) {
                setOnCheckedChangeListener { group, checkedId, isChecked -> it.onNext(checkedIds.map { id -> (group.findViewById(id) as LabelToggle).text.toString()  }.toList())}
            }
        }}
}