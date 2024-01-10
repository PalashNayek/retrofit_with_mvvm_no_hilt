package com.palash.retrofitwithmvvm.repository

import com.palash.retrofitwithmvvm.models.QuoteListResponse

/*sealed class Response(val data : QuoteListResponse? = null, val errorMessage : String? = null){
    class Loading : Response()
    class Success() : Response()
    class Error() : Response()
}*/
sealed class Response<T>(val data : T? = null, val errorMessage : String? = null){
    class Loading<T> : Response<T>()
    class Success<T>(data : T? =null) : Response<T>(data = data)
    class Error<T>(errorMessage : String ) : Response<T>(errorMessage = errorMessage)
}
