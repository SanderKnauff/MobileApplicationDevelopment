package ooo.sansk.notepad.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import ooo.sansk.notepad.database.NoteRepository

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val noteRepository = NoteRepository(application.applicationContext)

    val note = noteRepository.getNotepad()

}
