function greet(x)
{
	return "hello, " + x + "!";
}

function SimpleGreeter(salutation)
{
	this.salutation = salutation;
}
SimpleGreeter.prototype.greet = function(whom)
{
	return this.salutation + ", " + whom + "!" ;
}