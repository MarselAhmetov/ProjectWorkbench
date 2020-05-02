<!-- Modal Notification -->
<div class="modal fade" id="notificationModal" tabindex="-1" role="dialog" aria-labelledby="notificationModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="notificationModalLabel">Notifications</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <#list friendRequests as request>
                    <button class="btn btn-success form-control" data-toggle="collapse"
                            href="#multiCollapseExample1"
                            role="button" aria-expanded="false" aria-controls="multiCollapseExample1">Friend request
                    </button>
                    <div class="collapse multi-collapse" id="multiCollapseExample1">
                        <div class="card card-body">
                            <h3>${request.sender.username}</h3>
                            <p>${request.message}</p>
                            <small>${request.date}</small>
                            <form action="/friendRequest" method="post">
                                <div class="form-group" align="right">
                                    <input type="hidden" name="request" value="${request.id}" class="form-control">
                                    <input type="hidden" name="sender" value="${request.sender.id}">
                                    <button type="submit" name="status" class="btn btn-success" value="accept">
                                        Accept
                                        request
                                    </button>
                                    <button type="submit" name="status" class="btn btn-danger" value="decline">
                                        Decline
                                        request
                                    </button>
                                </div>
                            </form>
                        </div>
                    </div>
                </#list>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>