/*
 * Copyright (c) 2018 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * Notwithstanding the foregoing, you may not use, copy, modify, merge, publish,
 * distribute, sublicense, create a derivative work, and/or sell copies of the
 * Software in any work that is designed, intended, or marketed for pedagogical or
 * instructional purposes related to programming, coding, application development,
 * or information technology.  Permission for such use, copying, modification,
 * merger, publication, distribution, sublicensing, creation of derivative works,
 * or sale is expressly withheld.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.raywenderlich.githubrepolist.ui.adapters

//1
//The import statement uses Kotlin Android Extensions.
// This allows you to reference components from the XML layout directly in your Kotlin code.
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.raywenderlich.githubrepolist.Item
import com.raywenderlich.githubrepolist.R
import com.raywenderlich.githubrepolist.RepoResult
import com.raywenderlich.githubrepolist.extensions.ctx
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_repo.view.*

class RepoListAdapter(private val repoList: RepoResult) : RecyclerView.Adapter<RepoListAdapter.ViewHolder>() {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    //2
    //R.layout.item_repo provides the layout defined in item_repo.xml
    val view = LayoutInflater.from(parent.ctx).inflate(R.layout.item_repo, parent, false)
    return return ViewHolder(view)
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    //3
    holder.bindRepo(repoList.items[position])
  }

  //4
  //This response sets the list size rather than the hard-coded list.
  override fun getItemCount(): Int = repoList.items.size

  class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bindRepo(repo: Item) {

      //5
      //You pass Item, as defined earlier in the data class.
      itemView.username.text = repo.owner.login.orEmpty()
      //6
      //Then you populate the username text defined in item_repo.xml with the owner.login, defined in the data class definitions.
      itemView.repoName.text = repo.fullName.orEmpty()


      //7
      //Finally,populate the name of the repository and the repository description.
      itemView.repoDescription.text = repo.description.orEmpty()


      Picasso.get().load(repo.owner.avatarUrl).into(itemView.icon)

    }

  }

}

//An important best practice when dealing with JSON responses from an API: Don’t assume every value will be non-empty.
// If there’s no value for the field, make it an empty string.
// This practice also illustrates some of Kotlin’s safety features.
// Your app will no longer crash because it tried to access a null value.