package kr.co.domain.model

data class GetMySentenceRes(
    var count: Int,
    var countOpen: Int,
    var mySentence: List<MySentenceList>
)
