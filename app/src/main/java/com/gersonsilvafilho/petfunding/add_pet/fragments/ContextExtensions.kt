
package com.gersonsilvafilho.petfunding.add_pet.fragments

import com.nex3z.togglebuttongroup.button.LabelToggle
import io.reactivex.Observable
import org.jetbrains.anko.childrenSequence

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


fun com.nex3z.togglebuttongroup.SingleSelectToggleGroup.SetObjectWithName(name:String) {
    var selected = this.childrenSequence().filter { it is LabelToggle && it.text == name}.first().id
    this.check(selected)
}

fun com.nex3z.togglebuttongroup.MultiSelectToggleGroup.SetObjectsWithNames(name:List<String>) {
    var selected = this.childrenSequence().filter { it is LabelToggle && name.contains(it.text)}.map { it.id }
    for (i in selected) { this.check(i) }
}