(* ::Package:: *)




BeginPackage["lab7`"]

FileStream::usage = "";
ReadVertex::usage = "";
ReadEdge::usage = "";
ReadEdges::usage = "";
ReadBi::usage = "";
SystemOfEquations::usage = "";

Begin["`Private`"];

FileStream[filename_] := OpenRead[filename];
ReadVertex[filestream_] := Read[filestream, {Word,Number}][[2]];
ReadEdge[filestream_] := Read[filestream, {Word,Number}][[2]];
ReadEdges[filestream_, edge_] := ReadList[filestream,Expression,edge];
ReadBi[filestream_] := ReadList[filestream,String];
SystemOfEquations[equations_,vars_,edgesList_,vertexList_,array_,vertex_] := Module[{},
vars = Subscript[x,#]&/@edgesList

equations = Total/@(Subscript[x,#]&/@(edgesList//Cases[#\[DirectedEdge]_])&)/@vertexList- Total/@(Subscript[x,#]&/@(edgesList//Cases[_\[DirectedEdge]#])&)/@vertexList;
Solve[equations==array,vars];
equations==array/.%//Simplify
equations=equations[[#]] == array[[#]]&/@Range[vertex];
Row[{MatrixForm@equations,MatrixForm@array},"="]
];
End[]
EndPackage[]






