<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link rel="stylesheet" href="../../css/time/default.css" />
	<link rel="stylesheet" href="../../css/time/normalize.css" />
	<link rel="stylesheet" href="../../css/bootstrap.min.css" />
	<link rel="stylesheet" href="../../css/justified-nav.css" />
	<link rel="stylesheet" href="../../css/game.css" />
    <!-- 禁止橫向滾動 -->
    <meta name="viewport" content="width=device-width, initial-scale=1,user-scalable=0" />
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>記憶翻牌大考驗</title>
    <style>
    	#music {
    		display: inline;
    		margin: auto;
    		margin-top: 40px;
    		margin-left: 20px;
    		position: absolute;
    	}
    	#timediv {
    		position: absolute;
    		top: 150px;
    		right: 200px;
    		font-size: 30px;
    	}
    	#Start {
    		position: absolute;
    		clear: left;
    		margin: auto;
    		top: 250px;
    		right: 250px;
			font-size: 20px;
    	}
    	span {
    		font-family: Microsoft JhengHei;
    	}
    	.scene {
    		margin-top:20px;
    		width: 1300px;
    	}
    	#music {
    		position: absolute;
    		top: 300px;
    		right: 250px;
    	}
    	#cheat {
    		position: absolute;
    		top: 400px;
    		right: 250px;
    	}
    	#loose {
    		position: absolute;
    		top: 450px;
    		right: 250px;
    	}

    </style>
    
</head>
<body onload="reciprocal()">
	<%@ include file="../header.jsp"%>
	<div style="height:110px"></div>
	<div id="timediv">
		<span id="time"></span>秒後遊戲結束
	</div>
<!-- 得獎畫面，是假的QRcode，以後可以試著用 真的+隨機 獎勵-->
    <div class="turning2" id="win">
        <span>　　　　　　　恭喜獲得本飯店精油SPA優惠券一張</span><img src="../../img/memoryGame/fake.png">
    </div>
<!-- 顯示圖片的地方 -->
    <div class="scene turning1" id="scene">
    </div>

    <div id="Start" class="demo">重新開始</div>
	<button id="music" type="button" class="btn btn-default">
		<span id="mutedpic" class="glyphicon glyphicon-volume-off" ></span>
	</button>
	<button id="cheat" type="button" >作弊扭</button>
	<button id="loose" type="button" >快速失敗</button>
	
    <!--背景音樂-->
    <audio id="audio" class="Bgmusic"  autoplay="autoplay" loop="loop">
        <source src="../../music/back.mp3" type="audio/mp3"/>
    </audio>


    <script type="text/javascript">
		//讓之後的$(id名)可以直接調用該物件
   		 var $ = function (obj) { return document.getElementById(obj); }
		
		//遊戲時間30秒
    	var t =setTimeout("location.href='<c:url value="/01_Client/_23_Game/GameLost.jsp"/>'",30000)
		//計時
		var r ;
		var s = 30;
     	function reciprocal(){
     		if(s>=0){
     	  $("time").innerHTML=s;
		  r =  setTimeout(reciprocal,1000);      
		  s -= 1;
     		}
		}

        //數字代表圖片
        var model = [1, 2, 3, 4, 5, 6, 1, 2, 3, 4, 5, 6];
       //將model裡著順序打亂
        function fn() { return Math.random() - Math.random(); }
        model.sort(fn);  //為什麼要特地傳入fn：sort()會按ASCII排
        var html = "";
        //插入html敘述
        for (var i = 0, len = model.length; i < len; i++) {
            var url = "../../img/memoryGame/room" + model[i] + ".jpg"; //圖片位址
            html += '<div class="box" attr=' + model[i] + ' id="box' + i + '"><span class="bg" id="bg' + i + '"></span><img class="img" id="img' + i + '" src=' + url + ' /></div>';
        }
        $("scene").innerHTML = html; // 顯示全部圖片數據
        //調整圖片比例
        function onresize() {
            var picbox = document.getElementById("box1");
            var imgs, bgs;
            var picbox_w = picbox.offsetWidth;
            var picbox_h = picbox_w * 4 / 5;
            for (var i = 0; i < 12; i++) {  //設定圖片，總數量如果有改這裡也要改
                picbox = document.getElementById("box" + i); //每個圖片的div
                picbox.style.height = picbox_h + 'px';
                imgs = document.getElementById("img" + i); //圖片本身
                imgs.style.width = picbox_w + 'px';
                imgs.style.height = picbox_h + 'px';
                bgs = document.getElementById("bg" + i); //卡牌背面
                bgs.style.width = picbox_w + 'px';
                bgs.style.height = picbox_h + 'px';
            }
        }
        onresize();
        var cacheObj = null; //緩存
        var aBtn = $("scene").getElementsByTagName("div"); //div scene下的div圖片
        var count = 0;//配對數
        var count_sum = 0;//點選次數
        for (var i = 0, len = aBtn.length; i < len; i++) {
            aBtn[i].onclick = function () {  //點擊圖片
                var self = this; //點擊圖片的資訊
                count_sum++;
                if (cacheObj == self) { //判斷是否翻牌，if已翻開，則不做接下來動作(不重複點擊)
                    return;
                }
                self.className = "box hover";  //翻牌
                if (cacheObj == null)// if卡片本身未翻開，場上無翻開的卡片
                {
                    cacheObj = self;
                }
                else { //場上已有一張卡片被翻開，開始判斷是否相同
                    if (cacheObj.getAttribute("attr") == self.getAttribute("attr"))// 翻開的卡片是否與場上另一相同
                    {
                        self.className = cacheObj.className = "box hover";
                        self.onclick = cacheObj.onclick = null;
                        cacheObj = null;
                        count++; //一組卡片成功
                    }
                    else {
                        setTimeout(function () { //翻錯了，卡片蓋回去
                            self.className = "box";
                            if (cacheObj) {
                                cacheObj.className = "box";
                            }
                            cacheObj = null;
                        }, 300);
                    }
                }
                if (count == 6) { //獲勝
                    setTimeout(function () {
                        $("scene").className = "scene turning2";
                        $("win").className = "turning1";
                        clearTimeout(t);
                        clearTimeout(r);
                    }, 300)
                }
            }
        }
        $("music").onclick = function () {
        	var muted = $("audio").muted;
        	if(muted == false){
        		$("audio").muted = true;
        		$("mutedpic").className="glyphicon glyphicon-volume-up";
        		} else {
        			$("audio").muted = false;
        			$("mutedpic").className="glyphicon glyphicon-volume-off";
        		}
        	
        }
        //直接獲勝
        $("cheat").onclick = function () {
        	 $("scene").className = "scene turning2";
             $("win").className = "turning1";
             clearTimeout(t);
             clearTimeout(r);
        }
        
        $("loose").onclick = function () {
        	window.location.href="<c:url value="/01_Client/_23_Game/GameLost.jsp"/>";
       }
        
        
        $("Start").onclick = function () {
            location.reload();
        }
    </script>
	<%@ include file="../footer.jsp"%>
</body>

</html>