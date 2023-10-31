// 校验手机号
function verifyPhoneNumber(phone) {
    var req = /^1[3456789]\d{9}$/
    if (req.test(phone)) {
        return true;
    } else {
        return false;
    }
}

// 数据字典回显值
function dataDictionaryEcho(data, type) {
    var dicNameValue = $.ajax({
        url: ctx + '/getDicName',
        method: 'post',
        async: false,
        data: {
            data: data,
            type: type
        },
        success: function (res) {
            dicNameValue = res.data;
        }
    })
    return dicNameValue.responseJSON.data;
}