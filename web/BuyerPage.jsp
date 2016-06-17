<%@ page import="Objects.User" %>
<div>
    <div class="user-container">
        <div class="type-header">
            <span>Seller Page</span>
        </div>
        <div class="left-side-user">
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
            <div style="color: #990099">
                <span style="margin-right: 8px"><%=us.getRating()%>/10  </span>
                <span>voted: <%=us.getVoters()%></span>
            </div>
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
        </div>
        <div class="right-side-user">
            <div>
                <a href="#his">History</a>
            </div>
        </div>
    </div>
</div>
<div class="div-separator"></div>
<div>
    <div class="user-container">
        <div class="type-header">
            <span id="his">History</span>
        </div>
    </div>
</div>
