$(function (){
    $('#reqBtn1').on('click',reqAjax1);})
$(function (){
    $('#reqBnt2').on('click',reqAjax2);})


function reqAjax1() {
    $.ajax({
        url:'/reqAjax1'
        , method : 'GET'
        , success :  function(resp){
            if(resp =='cool'){
                console.log(resp);
                $('#req1').text("전송받은 데이터 : " + resp)
            }
            // console.log(resp);
            // $('#req1').text("전송받은 데이터 : " + resp)
        }
    })
}

function reqAjax2() {
    var name = $("#username").val();
    var phone = $("#phone").val();
    if(name.trim() =='' || phone.trim()==''){
        alert('데이터를 입력해주세요')
        return;}
    //서버로 보낼 데이터 준비 : 파라미터로 만들기 . json 으로 만들기
    var sendData = {"name":name,"phone":phone}
    $.ajax({
        url:'/reqAjax2'
        , method : 'POST'
        , data: JSON.stringify(sendData)
        ,contentType : 'application/json; charset=UTF-8'
        ,dataType : 'json'
        , success :function(resp){
            alert( JSON.stringify(sendData))
        }
    })
}

function reqAjax3() {
    $.ajax({
        method : 'GET',
        url:'/reqAjax3',
        success : function(resp){
            var respData = "replyseq : " + resp["replyseq"]+", boardseq : "+ resp["boardseq"]+", userid : "+ resp["userid"]+", replytext : "+ resp["replytext"]+", regdate : "+ resp["regdate"];
            $("#req3").html(respData);
        }
    })
}