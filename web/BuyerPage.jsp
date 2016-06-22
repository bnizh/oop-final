<%@ page import="Objects.User" %>
<div>
    <div class="user-container">
        <div class="type-header">
            <span>Seller Page</span>
        </div>
        < class="left-side-user">
        <div style="float:right;">
            <form id="img-edit">
                <div class="image-upload">
                    <label id="input-label" for="img-edit-input">
                        <img id="pic-edit-icon" class="edit-icon" src="edit.png"/>
                    </label>
                    <input id="img-edit-input" name="simage" accept="image/gif, image/jpeg, image/png" type="file"/>
                </div>
            </form>
        </div>

        <%User us = (User) request.getSession().getAttribute("user"); %>
        <img src="ImageLoader?FileName=<%=us.getImage()%>" style="width: 200px; height: 200px">
        <% out.println(" <div id=\"rate-result\">\n" +
                "                <label class=\"user-fields\" style=\" text-align:center; margin-right: 10%\">Rating: " + us.getRating() + "</label>\n" +
                "                <label class=\"user-fields\" style=\"text-align:center;\">voters: " + us.getVoters() + "</label></div>\n");%>
    </div>
    <div class="center-side-user">

        <div>
            <span style="font-size: 30px"><%=us.getUserName()%></span>
        </div>
        <form id="edit-name" class="edit-forms">
            <input type="text" name='sname' class="user-fields" value="<%=us.getName()%>" readonly title="Name">
            <img src="edit.png" class="edit-icon edit-icon-uname">
        </form>
        <form class="edit-forms">
            <label> Email:</label>
            <input type="text" name="semail" class="user-fields" value="<%=us.getEmail()%>" readonly title="Email">
        </form>
        <form class="edit-forms" id="edit-mob">
            <label> Mobil:</label>
            <input type="text" name="smob" class="user-fields" value=" <%=us.getMobileNumber()%>" readonly
                   title="Mobile">
            <img src="edit.png" class="edit-icon ">
        </form>
        <form>
            <input type="text" style="float:left;" id="pass-change-link" class="user-fields" value="Change Password"
                   readonly
                   title="change Password">
        </form>
    </div>
    <div class="right-side-user">
        <div>
            <a href="#his">History</a>
        </div>
    </div>
</div>
</div>
<div class="type-header" style="width:50%; margin-left:25%;font-size: 20px">
    <div style="width: 100%">
            <span id="pass-change-err-msg"
                  style="color:red; display: none ">Error has occurred during password change</span>
        <span id="pass-change-suc-msg" style="color:green; display: none ">Password has Changed Successfully</span>
        <span id="pass-change-wrong-msg" style="color:red; display: none ">Current password is wrong</span></div>
</div>
<form class="edit-forms" id="pass-change"
      style="display: none; width: 30%;    margin-left: 30%;   margin-bottom: 80px;">
    <label> Current Password:</label>
    <input type="password" name="curpassword" class="registration-input"
           title="current password">
    <label> New Password:</label>
    <input type="password" name="newpassword" class="registration-input"
           title=" NewPassword">
    <button id="pass-chnage-submit" style="width: 35%;margin-top: 10px " type="submit"
            class="button registration-submit-buy">Update
    </button>
</form>
</div>
<div class="div-separator"></div>
<div>
    <div class="user-container">
        <div class="type-header">
            <span id="his">History</span>
        </div>
    </div>
</div>
