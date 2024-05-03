data class Note(
        val id: Int = 0,
        val isDeleted: Boolean = false,
        val title: String,
        val text: String,
        val authorId: Int,
        val comments: MutableList<NoteComment> = mutableListOf<NoteComment>(),
        var lastCommentId: Int = 0
)

data class NoteComment(
        val id: Int,
        val isDeleted: Boolean = false,
        val commenterId: Int,
        val noteId: Int,
        val text: String,
        val replyToComment: Int? = null
)

data class User(
        val id: Int,
        val friends: MutableList<Int> = mutableListOf<Int>()
)

class NoteNotFoundExeption(message: String) : RuntimeException(message)

object NoteService {
    private var notes = emptyArray<Note>()
    private var lastNoteId: Int = 0

    fun add(note: Note): Int {
        notes += note.copy(id = ++lastNoteId)
        return notes.last().id
    }

    fun edit(noteId:Int, newText: String): Int {
        for ((index, note) in notes.withIndex()) {
            if (noteId == note.id && note.isDeleted == false) {
                notes[index] = notes[index].copy(text =newText)
                return 1
            }
        }
        println("Не найден id")
        return 0
    }

    fun delete(noteId: Int): Int {
        for ((index, note) in notes.withIndex()) {
            if (noteId == note.id) {
                notes[index] = notes[index].copy(isDeleted = true)
                return 1
            }
        }
        println("Не найден id")
        return 0
    }

    fun clearAll() {
        notes = emptyArray()
        lastNoteId = 0
    }

    fun showNotes() {
        for (note in notes) {
            if (!note.isDeleted) {
                print(note)
                print('\n')
            }
        }
    }

    fun createComment(newNoteComment: NoteComment): Int {
        for ((index, note) in notes.withIndex()) {
            if (note.id == newNoteComment.noteId && !note.isDeleted) {
                notes[index].comments += newNoteComment.copy(id = ++notes[index].lastCommentId)
                return 1
            }
        }
        return 0
    }

    fun deleteComment(noteId: Int, noteCommentToDelete: Int): Boolean {
        for ((index, note) in notes.withIndex()) {
            if (noteId == note.id) {
                for (comment in note.comments) {
                    note.comments[noteCommentToDelete-1] = note.comments[noteCommentToDelete-1].copy(isDeleted = true)
                    return true
                }
            }
        }
        println("Не найден id заметки для коммента")
        return false
    }

    fun editComment(noteId:Int, commentToEditId: Int, newText:String): Int {
        for ((index, note) in notes.withIndex()) {
            if (note.id == noteId && !note.isDeleted){
                for (comment in note.comments) {
                    if (commentToEditId ==comment.id ) {
                        note.comments[commentToEditId-1] = note.comments[commentToEditId-1].copy(text = newText)
                        return 1
                    }
                }

            }

        }
        println("Не найден id заметки для коммента")
        return 0
    }

    fun restoreComment(noteId:Int, noteCommentToRestore: Int): Int {
        for ((index, note) in notes.withIndex()) {
            if (noteId == note.id && !note.isDeleted) {
                for (comment in note.comments) {
                    note.comments[noteCommentToRestore-1] = note.comments[noteCommentToRestore-1].copy(isDeleted = false)
                    return 1
                }
            }
        }
        println("Не найден id заметки для коммента")
        return 0
    }

    fun get(user: User): MutableList<Note> {
        var notesList = mutableListOf<Note>()
        for ((index, note) in notes.withIndex()) {
            if (user.id == note.authorId && !note.isDeleted) {
                notesList.add(note)
            }
        }
        return notesList
    }

    fun getById(id: Int): Note {
        for ((index, note) in notes.withIndex()) {
            if (note.id == id && !note.isDeleted) {
                return note
            }
        }
        throw NoteNotFoundExeption("Заметки с id $id не существует ")
    }

    fun getComments(idNote: Int): MutableList<NoteComment> {
        var noteComments = mutableListOf<NoteComment>()

        for ((index, note) in notes.withIndex()) {
            if (idNote == note.id && !note.isDeleted) {
                for (comment in note.comments) {
                    if (!comment.isDeleted) {
                        noteComments.add(comment)
                    }
                }
            }
        }
        return noteComments
    }

    fun getFriendsNotes(user: User): MutableList<Note> {
        var notesList = mutableListOf<Note>()
        for ((index, author) in user.friends.withIndex()) {
            for ((index, note) in notes.withIndex()) {
                if (note.authorId == author && !note.isDeleted) {
                    notesList.add(note)
                }
            }
        }
        return notesList
    }
}

fun main(){
    val anya = User (1, mutableListOf(2,3))
    val kolya = User (2, mutableListOf(1))
    val petya = User (3, mutableListOf(1))

    val anyaNoteOne = Note (0, title = "Анина первая заметка", text = "Анин первый текст", authorId = 1)
    val anyaNoteTwo = Note (0, title = "Анина вторая заметка", text = "Анин второй текст", authorId = 1)
    val anyaNoteThree = Note (0, title = "Анина третья заметка", text = "Анин третий текст", authorId = 1)

    val kolyaNoteOne = Note (0, title = "Колина первая заметка", text = "Колин первый текст", authorId = 2)
    val kolyaNoteTwo = Note (0, title = "Колина вторая заметка", text = "Колин второй текст", authorId = 2)
    val kolyaNoteThree = Note (0, title = "Колина третья заметка", text = "Колин третий текст", authorId = 2)

    val petyaNoteOne = Note (0, title = "Петина первая заметка", text = "Петин первый текст", authorId = 3)
    val petyaNoteTwo = Note (0, title = "Петина вторая заметка", text = "Петин второй текст", authorId = 3)
    val petyaNoteThree = Note (0, title = "Петина третья заметка", text = "Петин третий текст", authorId = 3)

    NoteService.add(anyaNoteOne)
    NoteService.add(anyaNoteTwo)
    NoteService.add(anyaNoteThree)
    NoteService.add(kolyaNoteOne)
    NoteService.add(kolyaNoteTwo)
    NoteService.add(kolyaNoteThree)
    NoteService.add(petyaNoteOne)
    NoteService.add(petyaNoteTwo)
    NoteService.add(petyaNoteThree)

    /*
    println("Добавлены заметки")
    NoteService.showNotes()
    */
    NoteService.delete(1)
    /*
    println("Удаляю заметку Ани номер один")
    NoteService.showNotes()
    */

    val comKolToAn2 = NoteComment (0, false,2,2,"коммент Коли на вторую заметку Ани")
    val comKolToAn1 =NoteComment (0, false,2,1,"коммент Коли на удаленную заметку Ани")
    val comKolToAn3 = NoteComment (0, false,2,3,"коммент Коли на третью заметку Ани")

    println(NoteService.createComment(comKolToAn1))
    println(NoteService.createComment(comKolToAn2))
    println(NoteService.createComment(comKolToAn3))
    //NoteService.showNotes()
    //println(NoteService.getById(2))


    println("Удаляю коммент")
    NoteService.deleteComment(2,1)
    println(NoteService.getComments(2))
    println("Возвращаю коммент")
    NoteService.restoreComment(2,1)
    println(NoteService.getComments(2))


    /*
    println("Вывод заметок друзей")
    println(NoteService.getFriendsNotes(kolya))
    */
/*
    println(NoteService.getById(3))
    val editComm:String = "изменила комментарий 1 у заметки 3"
    NoteService.editComment(3, 1, editComm)
    println(NoteService.getById(3))
    */
/*
    println(NoteService.getById(3))
    val editNote = "новая заметка"
    NoteService.edit(3, editNote)
    println(NoteService.getById(3))
    */
}

