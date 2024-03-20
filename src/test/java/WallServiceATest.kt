import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class WallServiceATest {

    @Before
    fun clearBeforeTest() {
        WallServiceA.clear()
    }

    @Test
    fun add() {
        val testPost =  WallServiceA.add(PostA(123, "the first post",likes = mutableListOf<LikesA> ()))
        assertEquals(1,testPost.id)
    }

    @Test
    fun updateTrue() {
        WallServiceA.add(PostA(1, "the first post",likes = mutableListOf<LikesA> ()))
        val isUpd = WallServiceA.update((PostA(1, "the sc post",likes = mutableListOf<LikesA> ())))
        assertEquals(true, isUpd)
    }

    @Test
    fun updateFalse() {
        WallServiceA.add(PostA(1, "the first post",likes = mutableListOf<LikesA> ()))
        val isUpd = WallServiceA.update((PostA(123, "the sc post",likes = mutableListOf<LikesA> ())))
        assertEquals(false, isUpd)
    }
}