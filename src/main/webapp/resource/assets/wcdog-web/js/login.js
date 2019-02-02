$(function () {
    var $headerUser = $('#header-user');
    var $userInfoDialog = $('#user_info_dialog');

    initEvent();

    /**
     * 正则检验邮箱
     * email 传入邮箱
     * return true 表示验证通过
     */
    function check_email(email) {
        if (/^[\w\-\.]+@[\w\-]+(\.[a-zA-Z]{2,4}){1,2}$/.test(email)) {
            return true;
        }
    }

    /**
     * input 按键事件keyup
     */
    $("input[name]").keyup(function (e) {
        //禁止输入空格  把空格替换掉(空格的ASCII为32)
        if ($(this).attr('name') === "pass" && e.keyCode === 32) {
            $(this).val(function (i, v) {
                return $.trim(v);
            });
        }
        if ($.trim($(this).val()) !== "") {
            $(this).nextAll('span').eq(0).css({display: 'none'});
        }
    });

    //错误信息
    var succ_arr = [];

    /**
     * input失去焦点事件focusout
     * 这跟blur事件区别在于，他可以在父元素上检测子元素失去焦点的情况。
     */
    $("input[name]").focusout(function (e) {
        var msg = "";
        if ($.trim($(this).val()) === "") {
            if ($(this).attr('name') === 'userName') {
                succ_arr[0] = false;
                msg = "登入名为空";
            } else if ($(this).attr('name') === 'pass') {
                succ_arr[1] = false;
                msg = "密码为空";
            }
        } else {
            if ($(this).attr('name') === 'userName') {
                succ_arr[0] = true;
            } else if ($(this).attr('name') === 'pass') {
                succ_arr[1] = true;
            }
        }
        $(this).nextAll('span').eq(0).css({display: 'block'}).text(msg);
    });

    /**
     * Ajax用户注册
     */
    $("button[type='button']").click(function () {
        $("input[name]").focusout(); //让所有的input标记失去一次焦点来设置msg信息
        for (x in succ_arr) {
            if (succ_arr[x] === false) return;
        }
        //$("#login").removeClass("current");    
        var data = $('#login').serialize(); //序列化表单元素

        let name = $('#login input').eq(0).val();
        let psw = $('#login input').eq(1).val();

        $.ajax({
            url: '/wcdog/user/login',
            type: 'post',
            data: 'name=' + name + '&psw=' + psw,
            dataType: 'json',
            success: function (result) {
                if (result.code === 200) {
                    window.location.href = "main.html";
                } else {
                    alert(result.msg)
                }
            },
            complete: function (json) {

            }
        });

    });

    function initEvent() {
        userHeadEvent();
    }

    function userHeadEvent() {
        $headerUser.on('click', function (event) {
            //阻止事件冒泡
            event.stopPropagation();
            $userInfoDialog.slideToggle(200);
        });
        $(document).on('click', function () {
            if (!$userInfoDialog.is(':hidden')) {
                $userInfoDialog.hide();
            }
        })
    }

});