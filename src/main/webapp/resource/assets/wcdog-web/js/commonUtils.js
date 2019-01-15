function isEmpty(val) {
    var str = val.replace(/(^\s*)|(\s*$)/g, ''); //去除空格;
    if (str == '' || str == undefined || str == null) {
        return true;
    } else {
        return false;
    }
}