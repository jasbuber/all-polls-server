/**
 * Created by Jasbuber on 30/06/2016.
 */
$(document).ready(function () {

    $(document).on("click", ".toggle-poll-active", function (e) {
        var $this = $(this), $parent = $this.parent(), $pollId = $parent.find(".poll-id").val();

        jsRoutes.controllers.AdminController.ajaxSwitchPollActive($pollId).ajax({
            success: function (data) {
                if ($this.text().trim() == "activate") {
                    $this.text("deactivate");
                } else {
                    $this.text("activate");
                }
            }
        });
    });

    $(document).on("click", ".toggle-partial-active", function (e) {
        var $this = $(this), $parent = $this.parent(), $partialId = $parent.find(".partial-id").val();

        jsRoutes.controllers.AdminController.ajaxSwitchPartialPollActive($partialId).ajax({
            success: function (data) {
                if ($this.text().trim() == "activate") {
                    $this.text("deactivate");
                } else {
                    $this.text("activate");
                }
            }
        });

    });

    $(document).on("click", ".update-choice", function (e) {
        var $this = $(this), $parent = $this.parents("div.choice-wrapper"), $choiceId = $parent.find(".choice-id").val(),
            $name = $parent.find(".choice-name").val(), $universal = $parent.find(".choice-universal").val(),
            $value = $parent.find(".choice-value").val();

        jsRoutes.controllers.AdminController.ajaxUpdatePartialPollChoice($choiceId, $name, $universal, $value).ajax({
            success: function (data) {
                console.log(data);
            },
            error: function (data) {
                alert("error");
            }
        });

    });

});