package com.orbitbank.authenticationpartnerportal.model

class MappingDto {
    var mappingName: String? = null
    var userName: String? = null
    var productName: String? = null
    var customerName: String? = null
    var customerPan: String? = null
    var insuredName: String? = null
    var insuredPan: String? = null

    override fun toString(): String {
        return ("MappingDto [mappingName=" + mappingName + ", userName=" + userName + ", productName=" + productName
                + ", customerName=" + customerName + ", customerPan=" + customerPan + ", insuredName=" + insuredName
                + ", insuredPan=" + insuredPan + "]")
    }
}
