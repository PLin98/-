<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>个人资料</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="format-detection" content="telephone=no">
	<link rel="stylesheet" href="${rincar }/resources/layui/css/layui.css" media="all" />
	<link rel="stylesheet" href="${rincar }/resources/css/public.css" media="all" />
</head>
<body class="childrenBody">
<c:set var="Province" >北京市,天津市,上海市,重庆市,河北省,山西省,辽宁省,吉林省,黑龙江省,
		江苏省,浙江省,安徽省,福建省,江西省,山东省,河南省,湖北省,湖南省,广东省,海南省,四川省,贵州省,云南省,陕西省,
		甘肃省,青海省,台湾省,内蒙古自治区,广西壮族自治区,西藏自治区,宁夏回族自治区,新疆维吾尔自治区,香港特别行政区,澳门特别行政区</c:set>
<form class="layui-form layui-row">
	<div class="layui-col-md3 layui-col-xs12 user_right">
		<div class="layui-upload-list">
			<img class="layui-upload-img layui-circle userFaceBtn userAvatar" id="userFace">
		</div>
		<button type="button" class="layui-btn layui-btn-primary userFaceBtn"><i class="layui-icon">&#xe67c;</i> 掐指一算，我要换一个头像了</button>
	</div>
	<div class="layui-col-md6 layui-col-xs12">
		<div class="layui-form-item">
			<label class="layui-form-label">用户名</label>
			<div class="layui-input-block">
				<input type="text" value="${sessionScope.user.loginname}" disabled class="layui-input layui-disabled">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">用户组</label>
			<div class="layui-input-block">
				<input type="text" value="超级管理员" disabled class="layui-input layui-disabled">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">真实姓名</label>
			<div class="layui-input-block">
				<input type="text" name="realname"  placeholder="请输入真实姓名" lay-verify="required" class="layui-input realName">
			</div>
		</div>
		<div class="layui-form-item" pane="">
			<label class="layui-form-label">性别</label>
			<div class="layui-input-block userSex">
				<input type="radio" name="sex" value="男" title="男" checked="">
				<input type="radio" name="sex" value="女" title="女">
				<input type="radio" name="sex" value="保密" title="保密">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">手机号码</label>
			<div class="layui-input-block">
				<input type="tel" name="phone" value="" placeholder="请输入手机号码" lay-verify="phone" class="layui-input userPhone">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">出生年月</label>
			<div class="layui-input-block">
				<input type="text" value="" placeholder="请输入出生年月" lay-verify="userBirthday" readonly class="layui-input userBirthday">
			</div>
		</div>
		<div class="layui-form-item userAddress">
			<label class="layui-form-label">家庭住址</label>
			<!-- <div class="layui-input-block">
				<input type="text" name="province" value="" placeholder="请输入住址" lay-verify="province" class="layui-input userProvince">
			</div>
			 -->
			<div class="layui-input-inline">
				<select name="province" lay-filter="province" class="province">
				<c:forEach items="${Province}" var="prov">
					<option value="${prov}" <c:if test="${prov eq param.province}"> selected="selected"</c:if>>${prov}</option>
				</c:forEach>
				</select>
			</div>
			<%-- <div class="layui-input-inline">
				<select name="city" lay-filter="city" disabled>
					<option value="">请选择市</option>
				</select>
			</div>
			<div class="layui-input-inline">
				<select name="area" lay-filter="area" disabled>
					<option value="">请选择县/区</option>
				</select>
			</div> --%>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">掌握技术</label>
			<div class="layui-input-block userHobby">
				<input type="checkbox" name="like[javascript]" title="Javascript">
				<input type="checkbox" name="like[C#]" title="C#">
				<input type="checkbox" name="like[php]" title="PHP">
				<input type="checkbox" name="like[html]" title="HTML(5)">
				<input type="checkbox" name="like[css]" title="CSS(3)">
				<input type="checkbox" name="like[.net]" title=".net">
				<input type="checkbox" name="like[ASP]" title="ASP">
				<input type="checkbox" name="like[Angular]" title="Angular">
				<input type="checkbox" name="like[VUE]" title="VUE">
				<input type="checkbox" name="like[XML]" title="XML">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">邮箱</label>
			<div class="layui-input-block">
				<input type="text" value="" placeholder="请输入邮箱" lay-verify="email" class="layui-input userEmail">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">自我评价</label>
			<div class="layui-input-block">
				<textarea placeholder="请输入内容" class="layui-textarea myself"></textarea>
			</div>
		</div>
		<div class="layui-form-item">
			<div class="layui-input-block">
				<button class="layui-btn" lay-submit="" lay-filter="changeUser">立即提交</button>
				<button type="reset" class="layui-btn layui-btn-primary">重置</button>
			</div>
		</div>
	</div>
</form>
<script type="text/javascript" src="${rincar }/resources/layui/layui.js"></script>
<!-- <script type="text/javascript" src="userInfo.js"></script> -->
<script type="text/javascript" src="${rincar }/resources/js/cacheUserInfo.js"></script>
<script type="text/javascript" src="${rincar }/resources/js/address.js"></script>
<script type="text/javascript">
var form, $,areaData;
layui.config({
    base : "${rincar }/resources/js/"
}).extend({
    "address" : "address"
})
layui.use(['form','layer','upload','laydate',"address"],function(){
    form = layui.form;
    $ = layui.jquery;
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        upload = layui.upload,
        laydate = layui.laydate,
        address = layui.address;

    //上传头像
    upload.render({
        elem: '.userFaceBtn',
        url: '${rincar }/resources/json/userface.json',
        method : "get",  //此处是为了演示之用，实际使用中请将此删除，默认用post方式提交
        done: function(res, index, upload){
            var num = parseInt(4*Math.random());  //生成0-4的随机数，随机显示一个头像信息
            $('#userFace').attr('src',res.data[num].src);
            window.sessionStorage.setItem('userFace',res.data[num].src);
        }
    });

    //添加验证规则
    form.verify({
        userBirthday : function(value){
            if(!/^(\d{4})[\u4e00-\u9fa5]|[-\/](\d{1}|0\d{1}|1[0-2])([\u4e00-\u9fa5]|[-\/](\d{1}|0\d{1}|[1-2][0-9]|3[0-1]))*$/.test(value)){
                return "出生日期格式不正确！";
            }
        }
    })
    //选择出生日期
    laydate.render({
        elem: '.userBirthday',
        format: 'yyyy年MM月dd日',
        trigger: 'click',
        max : 0,
        mark : {"0-12-15":"生日"},
        done: function(value, date){
            if(date.month === 12 && date.date === 15){ //点击每年12月15日，弹出提示语
                layer.msg('今天是马哥的生日，也是layuicms2.0的发布日，快来送上祝福吧！');
            }
        }
    });

    //获取省信息
    //address.provinces();

    //提交个人资料
    form.on("submit(changeUser)",function(data){
        var index = layer.msg('提交中，请稍候',{icon: 16,time:false,shade:0.8});
        //将填写的用户信息存到session以便下次调取
        var key,userInfoHtml = '';
        userInfoHtml = {
            'realName' : $(".realName").val(),
            'sex' : data.field.sex,
            'userPhone' : $(".userPhone").val(),
            'userBirthday' : $(".userBirthday").val(),
            'province' : data.field.province,
            'city' : data.field.city,
            'area' : data.field.area,
            'userEmail' : $(".userEmail").val(),
            'myself' : $(".myself").val()
        };
        for(key in data.field){
            if(key.indexOf("like") != -1){
                userInfoHtml[key] = "on";
            }
        }
        window.localStorage.setItem("userInfo",JSON.stringify(userInfoHtml));
        //window.sessionStorage.setItem("userInfo",JSON.stringify(userInfoHtml));
      
        //提交表单发送请求
	    $.ajax({          
				  url: "${rincar}/user/changeUser.action",       
				  type: "post",                
				  async: false,                   
				  data: data.field,                  
				  dataType: "json",       
				  success: function (data) {      
					  if(data.msg!='1'){ 
						  layer.alert("修改成功",{icon: 1,time:1000},function () {
							    layer.close(layer.index);
				                window.parent.location.reload();
							    
				            });
					  } else{
					  		layer.alert("修改失败",{icon: 2,time:2000});
					  	}
					  form.render();
				  }   
				});
        return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
    })
})
</script>
</body>
</html>