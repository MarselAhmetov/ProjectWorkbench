<div class="modal fade" id="addDebtModal" tabindex="-1" role="dialog" aria-labelledby="addDebtLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="addDebtLabel">New Debt</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <form action="/debts" method="post">
                <div class="modal-body">
                    <div class="form-group">
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <label class="input-group-text" for="inputGroupSelect01">Debt</label>
                            </div>
                            <select class="form-control" name="whos" id="inputGroupSelect01" required>
                                <option value="friend">Friend</option>
                                <option value="user">Me</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <select class="form-control" name="currency" id="inputGroupSelect" required>
                                    <option value="RUBLE" selected>₽</option>
                                    <option value="DOLLAR">$</option>
                                </select>
                            </div>
                            <input type="number" class="form-control" name="debtCount" placeholder="Debt amount"
                                   required>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <label class="input-group-text">Date</label>
                            </div>
                            <input class="form-control" type="date" name="date">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <label class="input-group-text" for="inputFriendSelect">Friend</label>
                            </div>
                            <select class="form-control" name="friendId" id="inputFriendSelect" onclick="changeFriend()" required>
                                <option value="-1">None</option>
                                <#list user.friends as friend>
                                    <option value="${friend.id}">${friend.username}</option>
                                </#list>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" name="debtorName"  id="DebtorName"
                               placeholder="Fill this line if you did not choose friend" required>
                    </div>
                    <div class="form-group">
                        <textarea class="form-control" name="description" id="description" cols="30" rows="3" placeholder="Description"></textarea>
                    </div>

                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-warning">Save debt</button>
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                </div>
            </form>
        </div>
    </div>
</div>