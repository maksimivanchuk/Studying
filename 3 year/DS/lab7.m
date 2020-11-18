(* ::Package:: *)

BeginPackage["lab7`"];
 



FileStream[filename_] := OpenRead[filename]
ReadVertex[filestream_] := Read[filestream, {Word,Number}][[2]]
ReadEdge[filestream_] := Read[filestream, {Word,Number}][[2]]
ReadEdges[filestream_, edge_] := ReadList[filestream,Expression,edge]
ReadBi[filestream_] := ReadList[filestream,String]

SystemOfEquations[edgesList_,vertexList_,array_,vertex_] := Module[{},
equations = Total/@(Subscript[x,#]&/@(edgesList//Cases[#\[DirectedEdge]_])&)/@vertexList- Total/@(Subscript[x,#]&/@(edgesList//Cases[_\[DirectedEdge]#])&)/@vertexList;
equations=equations[[#]] == array[[#]]&/@Range[vertex];
Return[equations]
]

EquationsBalance[gArray_, gPred_, gDinastVertex_, gDir_,gEdgesList_,gTreeEdges_]:=Module[{}, 
xp=ConstantArray[0, vertex];
Map[{i=gDinastVertex[[#]],
xp[[i]]+=-gDir[[i]]*gArray[[i]],
xp[[gPred[[i]]]]+=gDir[[i]]*gDir[[gPred[[i]]]]*xp[[i]]} &,  Reverse[vertexList]];
arr=Subscript[x,#1]->0&/@Complement[edgesList, treeEdges];
xij=List[];
For[i=1,i<=vertex,i++,
If[gDir[[i]]==0,Continue[]]
If [gDir[[i]]==1,variable=gPred[[i]]\[DirectedEdge]i];
If[gDir[[i]]==-1,variable=i\[DirectedEdge]gPred[[i]]];
AppendTo[xij,Subscript[x,variable]->xp[[i]]];
];
result = Join[arr,xij];
Return[result];
]


rules={};
\[Tau]Function[\[Tau]_,pos_,edgesList_,pred_,dir_]:=(
If[dir[[\[Tau]]] ==1,AppendTo[rules,Flatten[{pos,Position[edgesList,pred[[\[Tau]]]\[DirectedEdge]\[Tau]]}]->1],AppendTo[rules,Flatten[{pos,Position[edgesList,\[Tau] \[DirectedEdge]pred[[\[Tau]]]]}]->-1]]);
\[Rho]Function[\[Rho]_,pos_,edgesList_,pred_,dir_]:=(
If[dir[[\[Rho]]] == 1,AppendTo[rules,Flatten[{pos,Position[edgesList,pred[[\[Rho]]]\[DirectedEdge]\[Rho]]}]->-1],AppendTo[rules,Flatten[{pos,Position[edgesList,\[Rho]\[DirectedEdge]pred[[\[Rho]]]]}]->1]]);

characteristic[edge_,listUn_,edgesList_,depth_,pred_,vertexList_,listUt_,dir_]:=Module[{},
\[Tau]=Part[edge,1];\[Rho]=Part[edge,2];
position=Position[listUn,edge];
AppendTo[rules,Flatten[{position,Position[edgesList,edge]}]->1];
If[depth[[\[Tau]]]>depth[[\[Rho]]],While[depth[[\[Tau]]]!=depth[[\[Rho]]],\[Tau]Function[\[Tau],position,edgesList,pred,dir];
	\[Tau]=pred[[\[Tau]]]],If[depth[[\[Tau]]]<depth[[\[Rho]]],
	While[depth[[\[Tau]]]!=depth[[\[Rho]]],\[Rho]Function[\[Rho],position,edgesList,pred,dir];
	\[Rho]=pred[[\[Rho]]]]]];
If[depth[[\[Tau]]]==depth[[\[Rho]]],While[\[Tau]!=\[Rho],\[Tau]Function[\[Tau],position,edgesList,pred,dir];
	\[Tau]=pred[[\[Tau]]];
	\[Rho]Function[\[Rho],position,edgesList,pred,dir];\[Rho]=pred[[\[Rho]]]]];
Return[Graph[vertexList,Append[listUt,edge],VertexSize->0.5, VertexLabels->Placed["Name", Center], VertexLabelStyle->Directive[Bold,Italic,20],EdgeShapeFunction->GraphElementData["Arrow","ArrowSize"->0.05],GraphLayout->"CircularEmbedding",GraphHighlight->edge]];
];

generalFunction[ij_,indepVars_,\[Delta]_,listUn_,result_,edgesList_]:=Module[{},
general=Total[indepVars[[#]]*\[Delta][[#,ij]]&/@Array[#&,Length[listUn]]]+ToExpression[StringSplit[ToString[result[[Position[ result,edgesList[[ij]]][[1]][[1]]]]],{"x",ToString[edgesList[[ij]]],"->"," ","\n"}][[1]]];
Return[general];]



EndPackage[];
























