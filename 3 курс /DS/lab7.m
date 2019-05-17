(* ::Package:: *)

BeginPackage["lab7`"]
Begin["`Private`"]

FileStream[filename_] := OpenRead[filename]
ReadVertex[filestream_] := Read[filestream, {Word,Number}][[2]]
ReadEdge[filestream_] := Read[filestream, {Word,Number}][[2]]
ReadEdges[filestream_, edge_] := ReadList[filestream,Expression,edge]
ReadBi[filestream_] := ReadList[filestream,String]

SystemOfEquations[edgesList_,vertexList_,array_,vertex_] := {

equations = Total/@(Subscript[x,#]&/@(edgesList//Cases[#\[DirectedEdge]_])&)/@vertexList- Total/@(Subscript[x,#]&/@(edgesList//Cases[_\[DirectedEdge]#])&)/@vertexList;
equations=equations[[#]] == array[[#]]&/@Range[vertex]
}
End[]
EndPackage[]












