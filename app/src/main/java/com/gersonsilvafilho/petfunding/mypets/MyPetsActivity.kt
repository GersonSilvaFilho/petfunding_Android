package com.gersonsilvafilho.petfunding.mypets

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.ViewGroup
import com.ericliu.asyncexpandablelist.CollectionView
import com.ericliu.asyncexpandablelist.async.AsyncExpandableListView
import com.ericliu.asyncexpandablelist.async.AsyncExpandableListViewCallbacks
import com.ericliu.asyncexpandablelist.async.AsyncHeaderViewHolder
import com.gersonsilvafilho.petfunding.R
import com.gersonsilvafilho.petfunding.addpet.AddPetActivity
import com.gersonsilvafilho.petfunding.chat.ChatActivity
import com.gersonsilvafilho.petfunding.detail.DetailActivity
import com.gersonsilvafilho.petfunding.model.pet.Pet
import com.gersonsilvafilho.petfunding.model.user.User
import com.gersonsilvafilho.petfunding.mypets.expandable.MyPetsParentViewHolder
import com.gersonsilvafilho.petfunding.mypets.expandable.MyPetsUserChildViewHolder
import com.gersonsilvafilho.petfunding.util.PetApplication
import org.jetbrains.anko.startActivity
import javax.inject.Inject


class MyPetsActivity : AppCompatActivity(), MyPetsContract.View, AsyncExpandableListViewCallbacks<Pet, User> {


    @Inject
    lateinit var  mActionsListener: MyPetsContract.Presenter

    private lateinit var  mLayoutManager: LinearLayoutManager

    private lateinit var  mAsyncExpandableListView: AsyncExpandableListView<Pet, User>

    var inventory: CollectionView.Inventory<Pet, User>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_pets)
        initDagger()
        setupToolbar()

        mAsyncExpandableListView = findViewById<AsyncExpandableListView<Pet, User>>(R.id.asyncExpandableCollectionView)
        mAsyncExpandableListView.setCallbacks(this)

        mLayoutManager = LinearLayoutManager(this)
    }

    private fun initDagger()
    {
        (application as PetApplication).get(this)
                .getUserComponent()!!
                .plus(MyPetsModule(this))
                .inject(this)
    }

    private fun setupToolbar()
    {
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun setAdapter(likedPets: List<Pet>) {
        inventory = CollectionView.Inventory<Pet, User>()

        for ((index, value) in likedPets.withIndex())
        {
            val group1 = inventory!!.newGroup(index) // groupOrdinal is the smallest, displayed first
            group1.headerItem = value
        }

        mAsyncExpandableListView.updateInventory(inventory)
    }

    override fun onPetClicked(): (Pet) -> Unit  = {
        mActionsListener.petSelected(it)

    }

    override fun onPetEdit(): (Pet) -> Unit  = {
        mActionsListener.petEdit(it)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.getItemId() === android.R.id.home)
        {
            finish()
        }

        return super.onOptionsItemSelected(item)
    }

    override fun startDetails(pet:Pet)
    {
        startActivity<DetailActivity>("pet" to pet)
    }

    override fun startEditPet(pet:Pet)
    {
        startActivity<AddPetActivity>("pet" to pet)
    }

    var startChatUser = {pet:Pet, userId:String ->
        startActivity<ChatActivity>("pet" to pet, "userId" to userId)
    }

    override fun newCollectionItemView(context: Context?, groupOrdinal: Int, parent: ViewGroup?): RecyclerView.ViewHolder {
        val v = LayoutInflater.from(context)
                .inflate(R.layout.user_chat_item, parent, false)

        return MyPetsUserChildViewHolder(v)
    }

    override fun onStartLoadingGroup(groupOrdinal: Int) {
        mActionsListener.getUsersFromPet(groupOrdinal, mAsyncExpandableListView.getHeader(groupOrdinal).uid)
    }

    override fun setUser(loadGroup:Int, user:List<User>)
    {
        mAsyncExpandableListView.onFinishLoadingGroup(loadGroup, user)
    }

    override fun bindCollectionHeaderView(context: Context?, holder: AsyncHeaderViewHolder?, groupOrdinal: Int, headerItem: Pet) {
        val myHeaderViewHolder = holder as MyPetsParentViewHolder
        myHeaderViewHolder.setPet(headerItem, onPetClicked(), onPetEdit(), groupOrdinal)
    }

    override fun newCollectionHeaderView(context: Context?, groupOrdinal: Int, parent: ViewGroup?): AsyncHeaderViewHolder {
        val v = LayoutInflater.from(context)
                .inflate(R.layout.mypet_item, parent, false)

        return MyPetsParentViewHolder(v, groupOrdinal, mAsyncExpandableListView)
    }

    override fun bindCollectionItemView(context: Context?, holder: RecyclerView.ViewHolder?, groupOrdinal: Int, item: User) {
        val myHeaderViewHolder = holder as MyPetsUserChildViewHolder
        myHeaderViewHolder.setUser(item,mAsyncExpandableListView.getHeader(groupOrdinal), startChatUser)
    }
}
