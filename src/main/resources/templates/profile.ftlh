<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link rel="stylesheet" href="../static/css/styles.css">
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
            integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
            crossorigin="anonymous"></script>
    <meta charset="UTF-8">
    <title>Profile</title>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="#">Debt</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
            </li>

            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown"
                   aria-haspopup="true" aria-expanded="false">
                    Dropdown
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item" href="#">Action</a>
                    <a class="dropdown-item" href="#">Another action</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="#">Something else here</a>
                </div>
            </li>
        </ul>
    </div>
</nav>
<br>
<br>
<div class="container">
    <div class="row personal-area-header">
        <div class="col-4">
            <div class="row">
                <div class="col-4">
                    <img src="http://placehold.it/100x100" alt="" style="border-radius: 50%">
                </div>
                <div class="col-6">
                    <div class="row"><h3 class="">${user.username}</h3></div>
                    <div class="row"><button type="button" class="btn btn-warning btn">Notifications</button></div>
                    <div class="row"><small><a href="/edit">Edit profile</a></small></div>
                </div>
            </div>
        </div>

        <div class="col-8 justify-content-sm-between personal-area-menu">
            <div class="row">
                <div class="col-4">
                    <div class="row">
                        <h5>Debtors count: xxxx</h5>
                    </div>
                    <div class="row">
                        <a href="/debtors" class="btn btn-warning">Debtors</a>
                        <button type="button" class="btn btn-warning" data-toggle="modal" data-target="#addDebtorModal">
                            +Debtor
                        </button>
                    </div>
                </div>
                <div class="col-4">
                    <div class="row">
                        <h5>Total debts: xxxx</h5>
                    </div>
                    <div class="row">
                        <a href="/debts" class="btn btn-warning">Debts</a>
                        <button type="button" class="btn btn-warning" data-toggle="modal" data-target="#addDebtModal">
                            +Debt
                        </button>
                    </div>
                </div>
                <div class="col-4">
                    <div class="row">
                        <h5>Your total debts: xxxx</h5>
                    </div>
                    <div class="row">
                        <a href="/friends" class="btn btn-warning">Friends</a>
                        <button type="button" class="btn btn-warning" data-toggle="modal" data-target="#addFriendModal">
                            +Friend
                        </button>
                    </div>
                </div>
            </div>
            <div class="row justify-content-sm-between">
            </div>
        </div>
    </div>
    <br>
    <div class="row text-center">
        <table class="table table-hover">
            <thead>
            <tr class="table-warning">
                <th scope="col">Name</th>
                <th scope="col">Total</th>
                <th scope="col">Debt count</th>
                <th scope="col">Go to</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <form action="/person">
                    <td>Mark</td>
                    <td>10002135</td>
                    <td>125</td>
                    <td><input class="btn btn-warning" type="submit" value="Name"></td>
                    <input type="hidden" value="id" name="personId">
                </form>
            </tr>
            </tbody>
        </table>
    </div>

    <!-- Button trigger modal -->


    <!-- Modal addDebtor -->
    <div class="modal fade" id="addDebtorModal" tabindex="-1" role="dialog" aria-labelledby="addDebtorLabel"
         aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="addDebtorLabel">New Debtor</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <form action="/login" method="get">
                    <div class="modal-body">

                        <div class="form-group">
                            <input type="text" class="form-control" name="username" placeholder="Debtor name">
                        </div>
                        <div class="form-group">
                            <select class="form-control" name="name">
                                <option value="none">None</option>
                                <option value="id">Friend 1</option>
                                <option value="id">Friend 2</option>
                            </select>
                        </div>

                    </div>
                    <div class="modal-footer">
                        <button type="submit" class="btn btn-warning">Create debtor</button>
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <!-- Modal addDebt -->
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
                <form action="/login" method="get">
                    <div class="modal-body">

                        <div class="form-group">
                            <input type="number" class="form-control" name="username" placeholder="Debt amount">
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-control" name="description" placeholder="Description">
                        </div>
                        <div class="form-group">
                            <select class="form-control" name="name">
                                <option value="id">Debtor 1</option>
                                <option value="id">Debtor 2</option>
                            </select>
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

    <!-- Modal addFriend -->
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
                    </div>
                    <div class="modal-footer">
                        <button type="submit" class="btn btn-warning">Send request</button>
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>