function startTime() {
    var today = new Date();
    var year = today.getFullYear();
    var month = today.getMonth() + 1;
    var day = today.getDay();
    var hour = today.getHours();
    var min = today.getMinutes();
    var second = today.getSeconds();
    month = checkTime(month);
    day = checkTime(day);
    min = checkTime(min);
    second = checkTime(second);
    document.getElementById('timeNow').innerHTML = day + " - " + month + " - " + year + " , " +
        hour + ":" + min + ":" + second;
    var t = setTimeout(startTime, 1000);
}
function checkTime(i) {
    if (i < 10) {i = "0" + i};  // add zero in front of numbers < 10
    return i;
}