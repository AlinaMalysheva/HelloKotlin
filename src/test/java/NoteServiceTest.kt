import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class NoteServiceTest {

    @Before
    fun clearBeforeTest() {
        NoteService.clearAll()
    }

    @Test
    fun add() {
        val testNote =  NoteService.add(Note(123, false,"the first note","h", 1))
        assertEquals(1, testNote)
    }

    @Test
    fun editTrue() {
        val testNote =  NoteService.add(Note(123, false,"the first note","h", 1))
        assertEquals(1,NoteService.edit(1, "g"))
    }

    @Test
    fun editFalse() {
        val testNote =  NoteService.add(Note(123, false,"the first note","h", 1))
        assertEquals(0,NoteService.edit(123, "g"))
    }

    @Test
    fun deleteTrue() {
        val testNote =  NoteService.add(Note(123, false,"the first note","h", 1))
        assertEquals(1,NoteService.delete(1))
    }

    @Test
    fun deleteFalse() {
        val testNote =  NoteService.add(Note(123, false,"the first note","h", 1))
        assertEquals(0,NoteService.delete(123))
    }

    @Test
    fun createCommentTrue() {
        val testNote =  NoteService.add(Note(123, false,"the first note","h", 1))
        val newCom = NoteComment(1,false,1,1,"e")
        assertEquals(1,NoteService.createComment(newCom))
    }

    @Test
    fun createCommentFalse() {
        val testNote =  NoteService.add(Note(123, false,"the first note","h", 1))
        val newCom = NoteComment(1,false,1,123,"e")
        assertEquals(0,NoteService.createComment(newCom))
    }

    @Test
    fun editCommentTrue() {
        val testNote =  NoteService.add(Note(123, false,"the first note","h", 1))
        val newCom = NoteComment(1,false,1,1,"e")
        NoteService.createComment(newCom)
        assertEquals(1,NoteService.editComment(1,1,"j"))
    }

    @Test
    fun editCommentFalse() {
        NoteService.add(Note(123, false,"the first note","h", 1))
        val newCom = NoteComment(1,false,1,1,"e")
        NoteService.createComment(newCom)
        assertEquals(0,NoteService.editComment(1,2,"j"))
    }

    @Test
    fun deleteCommentTrue() {
        NoteService.add(Note(123, false,"the first note","h", 1))
        val newCom = NoteComment(1,false,1,1,"e")
        NoteService.createComment(newCom)
        assertEquals(true, NoteService.deleteComment(1,1))
    }

    @Test
    fun deleteCommentFalse() {
        NoteService.add(Note(123, false,"the first note","h", 1))
        val newCom = NoteComment(1,false,1,1,"e")
        NoteService.createComment(newCom)
        assertEquals(false, NoteService.deleteComment(123,1))
    }

    @Test
    fun restoreCommentTrue() {
        NoteService.add(Note(123, false,"the first note","h", 1))
        val newCom = NoteComment(1,false,1,1,"e")
        NoteService.createComment(newCom)
        NoteService.deleteComment(123,1)
        assertEquals(1, NoteService.restoreComment(1,1))
    }

    @Test
    fun restoreCommentFalse() {
        NoteService.add(Note(123, false,"the first note","h", 1))
        val newCom = NoteComment(1,false,1,1,"e")
        NoteService.createComment(newCom)
        NoteService.deleteComment(123,1)
        assertEquals(0, NoteService.restoreComment(123,1))
    }

    @Test
    fun getUserNotes() {
        NoteService.add(Note(123, false,"the first note","h", 1))
        NoteService.add(Note(123, false,"the first note","h", 2))
        val user = User (1)
        assertEquals(mutableListOf(Note(1, false,"the first note","h", 1)), NoteService.get(user))
    }

    @Test
    fun getUserNotesFalse() {
        val user = User (3)
        assertEquals( mutableListOf<Note>(), NoteService.get(user))
    }

    @Test
    fun getById(){
        NoteService.add(Note(123, false,"the first note","h", 1))
        assertEquals(Note(1, false,"the first note","h", 1), NoteService.getById(1))

    }

    @Test (expected = NoteNotFoundExeption::class)
    fun shouldThrow(){
        NoteService.add(Note(123, false,"the first note","h", 1))
        NoteService.getById(123)
    }

    @Test
    fun getComments(){
        NoteService.add(Note(123, false,"the first note","h", 1))
        NoteService.createComment(newNoteComment = NoteComment(1,false,1,1,"h"))
        val listCom = mutableListOf<NoteComment>()
        listCom.add(0, NoteComment(1,false,1,1,"h"))
        assertEquals(listCom, NoteService.getComments(1))

    }

    @Test
    fun getFrNotes(){
        NoteService.add(Note(123, false,"the first note","h", 1))
        val testUser = User (2, mutableListOf(1,3))
        val list = mutableListOf<Note>(Note(1, false,"the first note","h", 1))
        assertEquals(list, NoteService.getFriendsNotes(testUser))

    }


}