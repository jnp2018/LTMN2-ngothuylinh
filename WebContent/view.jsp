<%@ page import="java.util.*" %>
<%@ page import="com.model.User" %>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE HTML>

<html>
<head>
</head>
<body>
<button><a href="/QuanLy/login?action=logout" style="text-decoration: none;float:right">Đăng Xuất</a></button>
<button style="margin-left: 280px;">
	<a href="http://localhost:8080/QuanLy/view" style="text-decoration: none;">Trang Chủ</a>
</button>
<button id="insert" style="margin-left: 10%;">Thêm mới</button>
<form action="view?action=search" method="post" style="margin-left: 60%;" >
    <input type="text" name="pid" id="pid" placeholder="Nhap ten can tim" autocomplete="off">
	<input type="submit" value="Tìm">
</form>

<form action="view?action=insert" method="post" class="form_insert"><br>
    <table width="800px" align="center"
           style="border:2px solid #007700;">
        <tr>
            <td colspan=5 align="center"
                style="background-color:#33FFFF">
                <h4>Thêm mới nhân viên</h4>
            </td>
        </tr>
        <tr style="background-color:lightgrey;">
            <td><b>Tên</b></td>
            <td><b>Phòng</b></td>
            <td><b>Số điện thoại</b></td>
        </tr>
        <tr style="background-color:lightgrey;">
            <td><input type="text" name="name" style="width: 255px;" autocomplete="off" required placeholder="Ho Ten"></td>
            <td>
            	<select name="department" class="form-control" style="width: 250px;">
			        <option value="Nhan Su">Nhan Su</option>
			        <option value="Ke Toan">Ke Toan</option>
			        <option value="Kinh Doanh">Kinh Doanh</option>
			        <option value="Le Tan">Le Tan</option>
			    </select>
			</td>
            <td><input type="text" name="sdt" style="width: 255px;" placeholder="0xxx (10 so)" pattern="^0\d{9}" autocomplete="off"></td>
        </tr>
        <tr>
            <td colspan=4 align="center">
                <button type="submit">Lưu</button>
            </td>
        </tr>
    </table>
</form>
<br>

<table width="800px" align="center"
       style="border:2px solid #3333FF;">
    <tr>
        <td colspan=5 align="center"
            style="background-color:#99FF99;">
            <h4>Danh sách nhân viên</h4></td>
    </tr>
    <tr style="background-color:lightgrey;">
        <td><b>ID</b></td>
        <td><b>Tên</b></td>
        <td><b>Phòng</b></td>
        <td><b>Số điện thoại</b></td>
        <td style="width:300px;"><b>Hành Động</b></td>
    </tr>
    <%
        int count = 0;
        String color = "#99FF99";
        if (request.getAttribute("piList") != null) {
            ArrayList<User> al = (ArrayList) request.getAttribute("piList");
            for (User pList: al) {
                if ((count % 2) == 0) {
                    color = "#CCFFFF";
                }
                count++;
    %>
    <form action="view?action=edit" method="post">
        <tr style="background-color:<%=color%>;" class="edit_sp">
            <td><input type="text" name="id" disabled required value="<%=pList.getID()%>"></td>
            <td><input type="text" name="name" disabled required value="<%=pList.getName()%>"></td>
            <%
            ArrayList<String> list = new ArrayList<String>();
            	list.add("Nhan Su");
            	list.add("Ke Toan");
            	list.add("Kinh Doanh");
            	list.add("Le Tan");
            %>
            <td>
            	<select name="department" class="form-control" disabled="disabled" style="width: 250px;">
            	<%for(int i=0;i<list.size(); i++) 
            		 if(list.get(i).equalsIgnoreCase(pList.getDepartment())){ %>
            				<option value="<%=list.get(i)%>" selected="selected"><%=list.get(i)%></option>
            			<%}else{%>
            			<option value="<%=list.get(i)%>"><%=list.get(i)%></option>
            			<%}%>
            		
			        
			    <% %>    
			    </select>
			</td>
            
            <td><input type="text" name="sdt" disabled required pattern="^0\d{9}" value="<%=pList.getSdt()%>"></td>
            <td >
                <button type="button" class="edit" style ="background-color: yellow;border:1px solid gray;">Sửa</button>
                <button type="button" idsp="<%=pList.getID()%>" style ="background-color: red;border:1px solid gray;" class="delete">Xóa</button>
            </td>
            <td class="save">
                <button type="submit" style="background-color:blue;border:1px solid gray;" >Lưu</button>
            </td>
        </tr>
    </form>
    <%
            }
        }
        if (count == 0) {
    %>
    <tr>
        <td colspan=5 align="center"
            style="background-color:#eeffee;color: red"><b>Không tìm thấy thông tin đã nhập !</b></td>
    </tr>
    <% }
    %>
</table>
<br/><br/>
<script src="js/jquery.js"></script>
<script>
    $(document).ready(function () {
        $('.save').hide();
        $('.edit').click(function () {
            $(this).parents(".edit_sp").find("input").prop("disabled", false);
            $(this).parents(".edit_sp").find("select").prop("disabled", false);
            $(this).parent().hide();
            $(this).parent().next().show();
        })

        $('.delete').click(function () {
            location.href = "view?action=delete&id="+$(this).attr("idsp");
        })
       $('.form_insert').hide();
        $('#insert').click(function () {
            if($('.form_insert').is(":visible")){
                $('.form_insert').hide();
            }else{
                $('.form_insert').show();
            }

        })
    })

</script>
</body>
</html>
