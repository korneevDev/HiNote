package github.mik0war.hinote.data.entity

import github.mik0war.hinote.core.MapperParametrised
import github.mik0war.hinote.core.MapperTime
import github.mik0war.hinote.core.TimeModel

data class NoteDataModel(
    private val id: Int,
    private val header: String,
    private val body: String,
    private val time: TimeModel,
    private val mainColor: Int,
    private val buttonsColor: Int
) {

    fun <T> map(mapper: MapperParametrised<T>) =
        mapper.map(id, header, body, time, mainColor, buttonsColor)

    fun <T> mapTime(timeMapper: MapperTime<T>): T = time.map(timeMapper, id)
}