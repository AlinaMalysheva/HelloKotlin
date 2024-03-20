data class PostA(
        val id:Int,
        val content: String,
        val likes: MutableList<LikesA>
)

data class LikesA (
        val likerID: Int
        )

object WallServiceA {

    private var posts = emptyArray<PostA>()
    private var currentLikerID = LikesA (1)
    private var lastPostId:Int = 0

    fun add (post: PostA) : PostA {
        posts += post.copy(id= ++lastPostId)
        return posts.last()
    }

    fun like (likerID: Int, postId: Int)  {
        currentLikerID = LikesA(likerID)
        for ((index, post) in posts.withIndex()){
            val extraMassive: MutableList<LikesA> = post.likes
            if (post.id == postId) {
                if (!post.likes.contains(currentLikerID)) {
                    extraMassive.add(currentLikerID)
                }  else {
                    extraMassive.remove(currentLikerID)
                }
                posts[index] = post.copy(likes = extraMassive)
            }

        }
    }

    fun showPosts() {
        for (post in posts) {
            print(post)
            print('\n')
        }
    }

    fun update(newPostA: PostA): Boolean {
        for ((index, post) in posts.withIndex()) {
            if (newPostA.id == post.id) {
                posts[index] = newPostA.copy(likes = post.likes)
                return true
            }
        }
        println("Не найден id")
        return false
    }

    fun clear() {
        posts = emptyArray()
        lastPostId = 0
    }




}

fun main() {
    val userIdOne = 1
    val userIdSec = 2
    val userIdTh = 3

    val likerID = LikesA(userIdOne)
    val likerIdSec = LikesA(userIdSec)
    val likerIdTh = LikesA(userIdTh)

    WallServiceA.add(PostA(123, "the first post",likes = mutableListOf<LikesA> (likerID, likerIdSec)))
    WallServiceA.add(PostA(1, "the sec post",likes = mutableListOf<LikesA>()))
    //я посмотрела видео разбор, лектору удавалось добавлять без прописывания всех параметров, а у меня требовало писать лайки. Почему?
    WallServiceA.add(PostA(1, "the thrd post",likes = mutableListOf<LikesA>()))
    WallServiceA.showPosts()
    print('\n')

    WallServiceA.like(userIdOne, 1)
    WallServiceA.like(userIdTh, 2)
    WallServiceA.showPosts()
    print('\n')

    WallServiceA.like(userIdOne, 2)
    WallServiceA.like(userIdTh, 2)
    WallServiceA.showPosts()
    print('\n')

    WallServiceA.update(PostA(id=1, content = "change content", likes = mutableListOf<LikesA>()))
    WallServiceA.showPosts()
    print('\n')

    WallServiceA.update(PostA(id=11, content = "change content", likes = mutableListOf<LikesA>()))
    WallServiceA.showPosts()
    print('\n')

    WallServiceA.clear()
    print("Почистили стену ")
    WallServiceA.showPosts()

    val testPost = PostA(123, "the first post",likes = mutableListOf<LikesA> ())
    WallServiceA.add(testPost)
    WallServiceA.showPosts()
    print('\n')

}


