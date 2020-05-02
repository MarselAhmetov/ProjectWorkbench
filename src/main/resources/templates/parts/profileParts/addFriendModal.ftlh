<div class="modal fade" id="addFriendModal" tabindex="-1" role="dialog" aria-labelledby="addFriendLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="addFriendLabel">New friend</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <form action="/friends" method="post">
                <div class="modal-body">
                    <div class="form-group">
                        <input type="email" class="form-control" name="email" placeholder="Friend email">
                    </div>
                    <div class="from-group">
                        <input type="text" class="form-control" name="message" placeholder="Message">
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-warning">Send request</button>
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                </div>
            </form>
        </div>
    </div>
</div>