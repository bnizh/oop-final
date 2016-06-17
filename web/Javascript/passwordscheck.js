passwordmatch = false;
ispasswordgood = false;
$(document).ready(function () {
    $('#reg-buy-pass').keyup(function () {
        $('#resultb').html(checkStrength($('#reg-buy-pass').val(), '#resultb'))
    })
    $('#reg-sel-pass').keyup(function () {
        $('#results').html(checkStrength($('#reg-sel-pass').val(), '#results'))
    })
    $('#reg-sel-pass-con').keyup(function () {
        var pas1 = $('#reg-sel-pass').val();
        var pas2 = $(this).val();

        if (pas1 != pas2) {
            $(this).css("background-color", "#D08080");
            passwordmatch = false;
        }
        else {
            passwordmatch = true;
            $(this).css("background-color", "#9FEF7D");
        }
    });
    $('#reg-buy-pass-con').keyup(function () {
        var pas1 = $('#reg-buy-pass').val();
        var pas2 = $(this).val();

        if (pas1 != pas2) {
            $(this).css("background-color", "#D08080");
            passwordmatch = false;
        }
        else {
            passwordmatch = true;
            $(this).css("background-color", "#9FEF7D");
        }
    });


    function checkStrength(password, id) {
        var strength = 0;
        if (password.length < 6) {
            $(id).removeClass();
            $(id).addClass('short');
            return 'Too short'
        }
        if (password.length > 7) strength += 1;
// If password contains both lower and uppercase characters, increase strength value.
        if (password.match(/([a-z].*[A-Z])|([A-Z].*[a-z])/)) strength += 1;
// If it has numbers and characters, increase strength value.
        if (password.match(/([a-zA-Z])/) && password.match(/([0-9])/)) strength += 1;
// If it has one special character, increase strength value.
        if (password.match(/([!,%,&,@,#,$,^,*,?,_,~])/)) strength += 1
// If it has two special characters, increase strength value.
        if (password.match(/(.*[!,%,&,@,#,$,^,*,?,_,~].*[!,%,&,@,#,$,^,*,?,_,~])/)) strength += 1
// Calculated strength value, we can return messages
// If value is less than 2
        if (strength < 2) {
            $(id).removeClass();
            $(id).addClass('weak');
            ispasswordgood = true;
            return 'Weak'
        } else if (strength == 2) {
            $(id).removeClass();
            $(id).addClass('good');
            ispasswordgood = true;
            return 'Good'
        } else {
            $(id).removeClass();
            $(id).addClass('strong');
            ispasswordgood = true;
            return 'Strong'
        }
    }
});