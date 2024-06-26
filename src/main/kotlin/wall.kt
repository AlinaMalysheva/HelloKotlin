data class PostA(
        val id: Int = 0,
        val content: String = " ",
        val likes: MutableList<LikesA> = mutableListOf<LikesA>(),

        val friends_only: Boolean = false,
        val isAds: Boolean = false,
        val isFavorite: Boolean = false,
        val isPin: Boolean = false,
        val view: Int = 0,
        val ownerId: Int = id,
        val comments: MutableList<Comment> = mutableListOf<Comment>(),
        var lastCommentId: Int = 0,

        //val comments: Array<Comment> = emptyArray<Comment>(),

        val original: PostA? = null,
        val repostCounter: Int? = null,
        val attachments: MutableList<Attachment> = mutableListOf<Attachment>()
)

interface Attachment {
    val type: String
}

data class Audio(
        val id: Int,
        val title: String,
        val duration: Long,
        val artist: String,
        val url: String
)

data class AttachmentAudio(
        override var type: String = "audio",
        var audio: Audio
) : Attachment

data class Video(
        val id: Int,
        val title: String,
        val duration: Long,
        val url: String
)

data class AttachmentVideo(
        override var type: String = "audio",
        var video: Video
) : Attachment

data class Doc(
        val id: Int,
        val title: String,
        val author: String,
        val url: String
)

data class AttachmentDoc(
        override var type: String = "doc",
        var audio: Doc
) : Attachment

data class Foto(
        val id: Int,
        val title: String,
        val url: String
)

data class AttachmentFoto(
        override var type: String = "foto",
        var audio: Foto
) : Attachment

data class Link(
        val id: Int,
        val title: String,
        val url: String
)

data class AttachmentLinc(
        override var type: String = "link",
        var audio: Link
) : Attachment

data class Comment(
        val id: Int,
        val commenterId: Int,
        val postId: Int,
        val text: String,
        val replyToComment: Int? = null
)

data class LikesA(
        val likerID: Int
)

class PostNotFoundExeption(message: String) : RuntimeException(message)

object WallServiceA {

    private var posts = emptyArray<PostA>()
    private var currentLikerID = LikesA(1)
    private var lastPostId: Int = 0

    fun add(post: PostA): PostA {
        posts += post.copy(id = ++lastPostId)
        return posts.last()
    }

    fun like(likerID: Int, postId: Int) {
        currentLikerID = LikesA(likerID)
        for ((index, post) in posts.withIndex()) {
            val extraMassive: MutableList<LikesA> = post.likes
            if (post.id == postId) {
                if (!post.likes.contains(currentLikerID)) {
                    extraMassive.add(currentLikerID)
                } else {
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

    fun isPostExist(postId: Int): Boolean {
        for ((index, post) in posts.withIndex()) {
            if (post.id == postId) {
                return true
            }
        }
        return false
    }

    fun createComment(postId: Int, comment: Comment): Comment {
        if (!isPostExist(postId)) {
            throw PostNotFoundExeption("Поста с id $postId не существует ")
        } else {
            for ((index, post) in posts.withIndex()) {
                if (post.id == postId) {
                    posts[index].comments += comment.copy(id = ++posts[index].lastCommentId)
                    return comment
                }
            }
            return comment
        }
    }
}

    fun main() {
        val userIdOne = 1
        val userIdSec = 2
        val userIdTh = 3

        val likerID = LikesA(userIdOne)
        val likerIdSec = LikesA(userIdSec)
        val likerIdTh = LikesA(userIdTh)

        WallServiceA.add(PostA(123, "the first post", likes = mutableListOf<LikesA>(likerID, likerIdSec)))
        WallServiceA.add(PostA(1, "the sec post", likes = mutableListOf<LikesA>()))
        WallServiceA.add(PostA(1, "the thrd post", likes = mutableListOf<LikesA>()))
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

        WallServiceA.update(PostA(id = 1, content = "change content", likes = mutableListOf<LikesA>()))
        WallServiceA.showPosts()
        print('\n')

        WallServiceA.update(PostA(id = 11, content = "change content", likes = mutableListOf<LikesA>()))
        WallServiceA.showPosts()
        print('\n')

        WallServiceA.clear()
        print("Почистили стену ")
        WallServiceA.showPosts()

        val testPost = PostA(123, "the first post", likes = mutableListOf<LikesA>())
        WallServiceA.add(testPost)
        WallServiceA.showPosts()
        print('\n')

        val audioTest = Audio(1, "1st audio", 1564789, "кто-то с кем-то", "www.fdf.dfdf")
        val videoTest = Video(1, "1st audio", 1564789, "www.fdf.dfdf")

        val audioAttTest = AttachmentAudio(audio = audioTest)
        val videoAttTest = AttachmentVideo(video = videoTest)

        WallServiceA.add(PostA(123,
                "the 4th post",
                likes = mutableListOf<LikesA>(likerID, likerIdSec),
                comments = mutableListOf<Comment>(),
                attachments = mutableListOf<Attachment>(audioAttTest, videoAttTest)
        ))
        WallServiceA.showPosts()
        print('\n')

        println("Добавляю комментарий")
        val myFirstComment = Comment(0, 1, 1, "комментарий к первому посту")
        println(WallServiceA.createComment(myFirstComment.postId, myFirstComment))

        println("Добавляю второй комментарий")
        val mySecComment = Comment(0, 2, 1256, "комментарий к несуществующему посту")
        println(WallServiceA.createComment(mySecComment.postId, mySecComment))
    }




