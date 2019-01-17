$(function () {
    var E = window.wangEditor;
    var editor = new E('#editor');
    editor.create();

    $('#joke_commit').click(function (event) {
        var title = $('#joke_title').val()
        var contentHtml = editor.txt.html();
        var temp = editor.txt.text();
        var content = temp.length > 200 ? temp.substring(0, 200) + '...' : temp;
        if (isEmpty(title)) {
            alert('请输入标题');
            return;
        }
        if (isEmpty(editor.txt.text())) {
            alert('请输入内容');
            return;
        }
        $.ajax({
            type: 'post',
            url: '/wcdog/joke/add',
            data: "title=" + title + "&content=" + content + "&contentHtml=" + contentHtml + "&jokeUserId=" + "1",
            dataType: 'json',
            success: function (result) {
                if (result.code === 200) {
                    alert('发表成功');
                } else {
                    alert('发表失败，请重试~');
                }
            }
        })
    })

    $('#joke_reset').click(function (event) {
        $('#joke_title').val('');
        editor.txt.html('');
    })

})