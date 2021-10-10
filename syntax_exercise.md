<syntax> -> begin <block> end
<block> -> <assign> ; <block> ; | <var> = ( <exp> )
<assign> -> <var> = <exp>
<exp> -> <exp> <opt> <exp> | <exp> <opt> <var> | <var> <opt> <var>
<opt> -> + | *
<var> _> A | B | C