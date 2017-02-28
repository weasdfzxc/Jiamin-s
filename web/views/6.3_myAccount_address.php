<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />
<link href="css/custom.css" rel="stylesheet" type="text/css" />
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>L-Gragon</title>
</head>
<body>
<br><br>
<div class = "container-fluid text-center" id="maincontainer">
    <div class="row">

        <div id="content" class="col-lg-9 col-sm-9">
            <!-- content starts -->
            <div class="panel panel-info">
		      <div class="panel-heading">
		      	Shipping Address
		      </div>
		      <div class="panel-body">
		      <form>
		      	<table class="table">
				    <thead>
				      <tr>
				        <th>No.</th>
				        <th>Address</th>
				        <th></th>
				      </tr>
				    </thead>
				    <tbody>
				      <tr>
				        <td>1</td>
				        <td>
				        	<div>
				        		<p>200 Hungtington Ave.</p>
				        		<p>Boston, MA 02115</p>
				        	</div>
				        </td>
				        <td>
				        	<a href="#" onclick="edit()"><i class="glyphicon glyphicon-pencil"></i></a>
				        	<a href="#" onclick="remove_a()"><i class="glyphicon glyphicon-remove"></i></a>
				        </td>
				      </tr>
				      <tr>
				        <td>2</td>
				        <td>
				        	<div>
				        		<p>2001 Hungtington Ave.</p>
				        		<p>Boston, MA 02115</p>
				        	</div>
				        </td>
				        <td>
				        	<a href="#" onclick="edit()"><i class="glyphicon glyphicon-pencil"></i></a>
				        	<a href="#" onclick="remove_a()"><i class="glyphicon glyphicon-remove"></i></a>
				        </td>
				      </tr>
				    </tbody>      	
				  </table>
					<center>
				      <button type="button" class="btn btn-primary btn-block" onclick="add()">Add New</button>
				      	</center>
				  </form>
		      </div>
		    </div>
		</div>

</div>

</div>
<br>
<br>
</body>
</html>