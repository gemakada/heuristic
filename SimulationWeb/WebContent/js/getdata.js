/**
 * 
 */







$( document ).ready(function() {
	$("#form").submit(function(e) {
	 $("#loading-div-background").css({ opacity: 1.0 });
    console.log( "ready!" );
    var data=$("#form").serialize();
    console.log(data);
    $.ajax({
    	  dataType: "json",
    	  url: "http://localhost:8080/SimulationWeb/test",
    	  data: $("#form").serialize()
    	 
    	}).done(function (results) {
    		console.log(data);
    	    console.log(results.elements.edges);
    	    	var array=results.elements.nodes;
    	    	var array2=results.elements.edges;
    	    	var cost_array=results.cost
				var cy = window.cy = cytoscape({
					container: document.getElementById('cy'),
					showOverlay: false,
          boxSelectionEnabled: false,
          autounselectify: true,

					layout: {
						name: 'dagre'
					},

					style: [
						{
							selector: 'node',
							style: {
								'content': 'data(id)',
								'text-opacity': 0.5,
								'text-valign': 'center',
								'text-halign': 'right',
								'background-color': '#11479e'
							}
						},

						{
							selector: 'edge',
							style: {
								'width': 4,
								'target-arrow-shape': 'triangle',
								'line-color': '#9dbaea',
								'target-arrow-color': '#9dbaea',
								'curve-style': 'bezier'
							}
						}
					],

					
				});
				var x=-1;
				var k=-1;
				
				for (var i=0; i<array.length; i++) {
					if (array[i].data.type=='NodeB') {
						console.log("mpike");
					cy.add(
					        { group: "nodes", data: { id: array[i].data.id }, position: { x: 100+100*i, y: 100 } }
					        
					      );
					}
					if (array[i].data.type=='Rnc') {
						x++;
						console.log("mpike");
					cy.add(
					        { group: "nodes", data: { id: array[i].data.id }, position: { x: 100+100*x, y: 200 } }
					        
					      );
					}
					
					if (array[i].data.type=='Sgsn') {
						k++;
						console.log("mpike");
					cy.add(
					        { group: "nodes", data: { id: array[i].data.id }, position: { x: 100+100*k, y: 300 } }
					        
					      );
					}
					
					
					
				}
				for (i=0; i<array2.length; i++) {
					cy.add(
							{ group: "edges", data: { id: "e"+i.toString(), source: array2[i].data.sourse, target: array2[i].data.target } }
					        
					      );
				}
				
				var data=[];
				var labels=[];
				for (i=0; i<cost_array.length; i++) {
					labels.push(i);
					data.push(cost_array[i].cost);
				}
				var canvas = document.getElementById("canvas");
				  var ctx = canvas.getContext("2d");

				  // Instantiate a new chart using 'data' (defined below)
				  
				  var dat = {
						    labels: labels,
						    datasets: [
						      {
						        label: "Solutions cost",
						        fillColor: "rgba(220,220,220,0.2)",
						        strokeColor: "rgba(220,220,220,1)",
						        pointColor: "rgba(220,220,220,1)",
						        pointStrokeColor: "#fff",
						        pointHighlightFill: "#fff",
						        pointHighlightStroke: "rgba(220,220,220,1)",
						        data: data
						      }
						    ]
						  };
				  var myNewChart = new Chart(ctx , {
					    type: "line",
					    data: dat,
					    
					    
					});
				
				
				
				
				
				
				

			});
    	
    
    
    e.preventDefault(); // avoid to execute the actual submit of the form.
	});
    
    
});