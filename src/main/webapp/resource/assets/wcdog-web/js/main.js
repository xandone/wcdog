$(function () {
    var jokeList = $('#joke-list');

    var banner = $('#banner');
    var bannerBox = $('#banner-box');
    var bannerImgs;
    var boxPoints;
    var currentIndex = 0;

    getjokeList();
    getBannerImgList();

    function getjokeList(page, count) {
        $.ajax({
            url: '/wcdog/joke/jokelist',
            type: 'GET',
            dataType: 'json',
            data: {
                page: 0,
                row: 10
            },
            success: function (result) {
                createJokeList(result.data);
            }
        });
    }

    function getBannerImgList(page, count) {
        $.ajax({
            url: '/wcdog/banner/list',
            type: 'GET',
            dataType: 'json',
            success: function (result) {
                createBanner(result.data);
            }
        });
    }

    function createJokeList(result) {
        var jokeItemParent = $('<ul></ul>');
        jokeList.html(jokeItemParent);

        for (var i = 0; i < result.length; i++) {
            var jokeItem = $('<li></li>');
            var temp = '<div class="joke-item">' +
                '<a class="joke-title" href="javascript:;">' + result[i].title + '</a>' +
                '<div class="joke-author"> ' +
                '<div class="joke-author-icon-root">' +
                '<img id="joke-author-icon" class="joke-author-icon"src=' + result[i].jokeUserIcon + ' alt="">' +
                '<div id="joke-author-dialog" class="joke-author-dialog">' +
                '<img src="imgs/headicon.jpg" alt="">' +
                '<span class="author-dialog-name">狗蛋</span>' +
                '<div class="joke-dialog-line"></div>' +
                '<span class="author-dialog-say">2019年的第一场雪~</span>' +
                '</div>' +
                '</div>' +
                '<span>' + result[i].jokeUserNick + '</span>' +
                '</div>' +
                '<div class="joke-content">' +
                '<span>' + result[i].content + '</span>' +
                '</div>' +
                '<span class="helpful"><img src="imgs/zan.png" alt="">' + result[i].articleLikeCount + '</span>' +
                '<span id="helpful_comment_icon" class="helpful"><img src="imgs/comment_icon.png" alt="">' + result[i].articleCommentCount + '</span>' +
                '<span class="joke-date">' + result[i].postTime + '</span>' +
                '<div id="reply_root" class="reply_root"><div class="reply_joke" contenteditable="true"></div>' +
                '<div class="reply_btn_root"><button class="reply_joke_btn">回复</button></div></div>' +
                ' </div>';

            jokeItem.html(temp);
            jokeItemParent.append(jokeItem);

            setEvent();
        }

        setReply();
    }

    function createBanner(result) {
        for (var i = 0; i < result.length; i++) {
            var imgTemp = '<a href="javascript:;" >' +
                '<img src=' + result[i].imgUrl + ' alt="">' +
                '</a>';
            banner.append(imgTemp);

            var point = '<li class="banner-box-bg"></li>';
            bannerBox.append(point);
        }

        bannerImgs = $('#banner a');
        boxPoints = $('#banner-box li');

        for (var i = 0; i < bannerImgs.length; i++) {
            $(bannerImgs[i]).removeClass();
            $(bannerImgs[i]).addClass('hidden');

            $(boxPoints[i]).removeClass('banner-box-select');
            $(boxPoints[i]).addClass('banner-box-bg');
        }

        $(bannerImgs[0]).addClass('show-banner-img');
        $(boxPoints[0]).addClass('banner-box-select');

        startLoop(bannerImgs.length);

    }


    function startLoop(len) {
        var timer = setInterval(function () {
            currentIndex++;
            if (currentIndex >= len) {
                currentIndex = 0;
            }

            for (var i = 0; i < bannerImgs.length; i++) {
                $(bannerImgs[i]).removeClass();
                $(bannerImgs[i]).addClass('hidden');

                $(boxPoints[i]).removeClass('banner-box-select');
                $(boxPoints[i]).addClass('banner-box-bg');
            }

            $(bannerImgs[currentIndex]).addClass('show-banner-img');
            $(boxPoints[currentIndex]).addClass('banner-box-select');

        }, 2000);
    }


    function setEvent() {
        $('#joke-author-icon').mouseover(function (event) {
            /* Act on the event */
            $('#joke-author-dialog').show('400', function () {

            });
        });

        $('#joke-author-icon').mouseout(function (event) {
            /* Act on the event */
            $('#joke-author-dialog').hide('400', function () {

            });
        });


    }

    function setReply() {
        // $(".panel-heading").on("click", function () {
        //     $(this).next().toggle(500);
        // });

        $('.helpful_comment_icon').click('click',function () {
            $(this).next().toggle("200");
        });
    }

})