package com.chyngyz.youtube.data

sealed class Event {
    class Test2 : Event()
    class Error : Event()
}

// для расширения open
open class Event2 {
}


