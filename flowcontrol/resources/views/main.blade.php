@extends('layouts.app')


@section('styles')
<style type="text/css">

	.scroll_list {
	    overflow: hidden;
	    padding: 10px;
	}

	.scroll_list div {
	    display: block;
	    padding: 5px 5px;
	    margin-bottom: 3px;
	}

	.slotItem {

	}

</style>
@endsection

@section('content')

<div class="card" style="width: 18rem; position:fixed; top:150px;left:20px">
  <div class="card-body">
    <h5 class="card-title" id="xxhtml">Current configuration</h5>
	<hr>
    <h6 class="card-subtitle mb-2 text-muted">Stable sequence period</h6>
    <p class="card-text">{{ $ssperiod }} minutes</p>
	<h6 class="card-subtitle mb-2 text-muted">Current runway</h6>
    <p class="card-text">{{ $rwy }}</p>
	<h6 class="card-subtitle mb-2 text-muted">Landing Rate</h6>
    <p class="card-text">{{ $landingrate }} minutes</p>
	<h6 class="card-subtitle mb-2 text-muted">Default flow control position</h6>
    <p class="card-text">CWP{{ $defaultflowcontrolposition }}</p>
    <a href="configurations" class="card-link">Change configurations</a>
   </div>
</div>

<div class="container p-0 m-0" style="position: absolute;width: 100%;left:0;z-index:50">
	<div class="row bg-info p-2 m-0" style="position:fixed;width: 100%;left:0">
		<div class="col">
			<div class="mx-auto">
				<h3 class="mb-3" style="padding-right: 250px;text-align:right">Auto sequence</h3>
			</div>
		</div>
		<div class="col">
			<div class="mx-auto">
				<h3 class="mb-3" style="padding-left: 240px">Manual sequence</h3>
			</div>
		</div>
	</div>	
</div>

<div id="container" class="container pb-5">
	<div class="container p-0 m-0" style="position:relative;width: 100%;left:0;z-index:1;" id="center">
		<div class="row  p-0 m-0" style="position:realtive;width: 100%;left:0">
			<div class="col p-0 m-0  opacity-50">
				<div class="mx-auto p-0 m-0">
					<h3 class="p-0 m-0 border-bottom border-secondary border-4"></h3>
				</div>
			</div>
		</div>	
	</div>
    <div class="row">
		<div class="mx-auto">
			<div class="col scroll_list" style="padding-top:100px"  id="all_list">
			</div>
		</div>
    </div>
	<div class="row">
		<div class="mx-auto">
			<div class="col bg-danger">

			</div>
		</div>
	</div>
</div>

<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">1</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        ...
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save changes</button>
      </div>
    </div>
  </div>
</div>

<!-- Modal -->
<div class="modal fade" id="exampleModal2" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">2</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        ...
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save changes</button>
      </div>
    </div>
  </div>
</div>

@endsection

@section('scripts')
<script type="text/javascript">

	$.ajaxSetup({
		headers: {
			'X-CSRF-TOKEN': $('meta[name="csrf-token"]').attr('content')
		}
	});

	var options = {
	    scroll: 9699, // duration of scroll animation between each item
	    pause: 1000, // time to stop on each item
	    height: 40,   // outer-height of element (height and padding), should instead be calculated
	    timeStep: 60000, // squence to move down every 1 minute
	    minutesIntoFuture: 60, // time into the future in minutes to show from current time 
	    minutesIntoPast: 5, // time into the past in minutes to show from current time 
	    timeScale: 'minutes', // time into the past in minutes to show from current time 
	    currentTimePxOffset: 100, // time into the past in minutes to show from current time 
	    idleTime: 600 // time to sit idle before page is automatically refreshed 
	};

	// Temporary disable ----------------------------------------------------------------------------------------------------
	$.contextMenu({
		selector: '.context-menu-one', 
		callback: function(key, options) {
			var eta = options.$trigger.attr("id").replace('auto_right_click', '');
			var callsign = $('#callsign_auto_item' + eta).html();
			
			if (key == "MoveManual") {
				moveFlightToManualSequence(eta,callsign);
			} 
		},
		items: {
			"MoveManual": {name: "Move to manual sequence", icon: "fa-arrow-right"},
			// "sep1": "---------",
			// "quit": {name: "Quit", icon: function(){
			// 	return 'context-menu-icon context-menu-icon-quit';
			// }}
		}
	});

	$.contextMenu({
		selector: '.context-menu-two', 
		callback: function(key, options) {
			var time = options.$trigger.attr("id").replace('auto_right_click', '');
			var callsign = $('#callsign_auto_item' + time).html();
			var sequence = "manual";
			
			if (key == "MoveManual") {
				moveFlightToManualSequence(time,callsign,sequence);
			} else if (key == "ReserveSlot") {
				reserveSlot(time,callsign);
			}
		},
		items: {
			"ReserveSlot": {name: "Reserve slot", icon: "fa-bookmark"},
			// "sep1": "---------",
			// "quit": {name: "Quit", icon: function(){
			// 	return 'context-menu-icon context-menu-icon-quit';
			// }}
		}
	});

	//$("html").on("mouseup", function (e) {
	//    var l = $(e.target);
	//    if (l[0].className.indexOf("popover-dismiss") == -1) {
	//        $(".popover-dismiss").each(function () {
	//            $(this).popover("hide");
	//        });
	//    }
	//});


	// Temporary disable ----------------------------------------------------------------------------------------------------

	var totalSlotCount;

	// Initialize variables and setup Slots
	function init() {

		totalSlotCount = options.minutesIntoFuture + options.minutesIntoPast;

		buildSlots();

		step();
		readFlightData();

		setInterval(step, options.timeStep + 500);
		setInterval(readFlightData, 5000);

		setIdle(function() {
		    location.href = location.href;
		}, options.idleTime); // 10 minutes

		//var popoverTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="popover"]'));
		//var popoverList = popoverTriggerList.map(function (popoverTriggerEl) {
		//  return new bootstrap.Popover(popoverTriggerEl);
		//});
	}

	// Runs every options.timeStep 
	function step() {
		addFutureSlot();
		removePastSlot();
	}

	// function to move to manual
	function moveFlightToManualSequence(time, callsign, sequencetype) {
		// var now = moment.utc();
		// var xtime = now.format('YYYY-MM-DD') + time;
		// var formatted_time = moment(xtime, "YYYY-MM-DD HHmm");;
		// ztime = formatted_time.format('YYYY-MM-DD HH:mm');

		var formatted_time = moment(time, "YYYYMMDDHHmm");

		console.log(formatted_time.format("YYYY-MM-DDTHH:mm"));

		$.ajax({
			method: "POST",
			url: "actions/moveflighttomanual", 
			data: {"eta":formatted_time.format("YYYY-MM-DDTHH:mm"), "callsign":callsign},
            success: function(result) {
				if (result == "success") {
					// Remove from Auto list
					$('#callsign_auto_item_icon' + time).remove();
					$('#callsign_auto_item' + time).remove();
					$('#auto_right_click' + time).removeClass("context-menu-one");
				}
			}
		});
	}
	
	// Reserve Slot
	function reserveSlot(time) {
		// Create XML and send to file.
		var now = moment.utc();
		var xtime = now.format('YYYY-MM-DD ') + time;
		var formatted_time = moment(xtime, "YYYY-MM-DD HHmm");;
		ztime = formatted_time.format('YYYY-MM-DD HH:mm');

		$.ajax({
			method: "POST",
			url: "actions/reservetimeslot", 
			data: {"time":ztime},
            success: function(result) {
				if (result == "success") {
					// Mark div as reserved
					// $('#callsign_auto_item_icon' + time).remove();
					// $('#callsign_auto_item' + time).remove();
					// $('#auto_right_click' + time).removeClass("context-menu-one");
				}
			}
		});
	}

	// DIV Slot Center template build
	function divSlotAll(date, prefix_id) {
		var popoverContent = '';
		popoverContent += '';


		var div = '';
		div += '<div class="container p-0 m-0" id="all_' + prefix_id + date.format('YYYYMMDDHHmm') + '">';
		div += '	<div class="d-flex flex-nowrap justify-content-center slotItem p-0 m-0" style="display:none;" id="' + prefix_id + date.format('YYYYMMDDHHmm') + '">';
		div += '		<div class="col-5 p-0 m-0">';
		div += '			<div class="container p-0 m-0">';
		div += '				<div class="d-flex flex-nowrap justify-content-center slotItem p-0 m-0" style="display:none;">';
		div += '					<div class="btn col-4 m-0 p-0 card context-menu-two" style="border-radius:0" id="auto_right_click' + date.format('YYYYMMDDHHmm') + '">';
		div += '						<div class="card-body p-0 m-0 popover-dismiss" data-bs-toggle="popover" data-bs-placement="top" data-bs-content="And heres some amazing content. Its very engaging. Right?">';
		div += '							<div class="container p-0 m-0">';
		div += '								<div class="d-flex flex-nowrap p-0 m-0">';
		div += '									<div style="font-size:12px" class="col p-0 m-0" id="callsign_auto_item_icon' + date.format('YYYYMMDDHHmm') + '"></div>';
		div += '									<div style="font-size:12px" class="col p-0 m-0" id="callsign_auto_item' + date.format('YYYYMMDDHHmm') + '"></div>';
		div += '									<div style="font-size:12px" class="col p-0 m-0" id="callsign_auto_item_regis' + date.format('YYYYMMDDHHmm') + '"></div>';
		div += '								</div>';
		div += '								<div class="d-flex flex-nowrap p-0 m-0 ml-3">';
		div += '									<div style="font-size:12px" class="col p-0 m-0"></div>';
		div += '									<div style="font-size:12px" class="col p-0 m-0" id="callsign_auto_item_ac_type' + date.format('YYYYMMDDHHmm') + '"></div>';
		div += '									<div style="font-size:12px" class="col p-0 m-0" id="callsign_auto_item_wtur' + date.format('YYYYMMDDHHmm') + '"></div>';
		div += '									<div style="font-size:12px" class="col p-0 m-0 bg-success text-light" id="callsign_auto_item_feeder_fix' + date.format('YYYYMMDDHHmm') + '"></div>';
		div += '								</div>';
		div += '							</div>';
		div += '						</div>';
		div += '					</div>';
		div += '					<div class="col-2 m-0 p-3 border border-dark border-1">' + date.format('HH:mm') + '</div>';
		div += '					<div class="col-4 m-0 p-0 card" style="border-radius:0">';
		div += '						<div class="col card-body">';
		div += '						</div>';
		div += '					</div>';
		div += '				</div>';
		div += '			</div>';
		div += '		</div>';
		div += '		<div class="col-2 p-0 m-0" id="current_time"></div>';
		div += '		<div class="col-5 p-0 m-0">';
		div += '			<div class="container p-0 m-0">';
		div += '				<div class="d-flex flex-nowrap justify-content-center slotItem p-0 m-0" style="display:none;">';
		div += '					<div class="col-4 m-0 p-0 card" style="border-radius:0">';
		div += '						<div class="card-body p-0 m-0 popover-dismiss" data-bs-toggle="popover" data-bs-placement="top" data-bs-content="And heres some amazing content. Its very engaging. Right?">';
		div += '							<div class="container p-0 m-0">';
		div += '								<div class="d-flex flex-nowrap p-0 m-0">';
		div += '									<div style="font-size:12px" class="col p-0 m-0" id="callsign_manual_item_icon' + date.format('YYYYMMDDHHmm') + '"></div>';
		div += '									<div style="font-size:12px" class="col p-0 m-0" id="callsign_manual_item' + date.format('YYYYMMDDHHmm') + '"></div>';
		div += '									<div style="font-size:12px" class="col p-0 m-0" id="callsign_manual_item_regis' + date.format('YYYYMMDDHHmm') + '"></div>';
		div += '								</div>';
		div += '								<div class="d-flex flex-nowrap p-0 m-0 ml-3">';
		div += '									<div style="font-size:12px" class="col p-0 m-0"></div>';
		div += '									<div style="font-size:12px" class="col p-0 m-0" id="callsign_manual_item_ac_type' + date.format('YYYYMMDDHHmm') + '"></div>';
		div += '									<div style="font-size:12px" class="col p-0 m-0" id="callsign_manual_item_wtur' + date.format('YYYYMMDDHHmm') + '"></div>';
		div += '								</div>';
		div += '							</div>';
		div += '						</div>';
		div += '					</div>';
		div += '					<div class="col-2 m-0 p-3 border border-dark border-1">' + date.format('HH:mm') + '</div>';
		div += '					<div class="col-4 m-0 p-0 card" style="border-radius:0">';
		div += '						<div class="col card-body">';
		div += '						</div>';
		div += '					</div>';
		div += '				</div>';
		div += '			</div>';
		div += '		</div>';
		div += '	</div>';
		div += '</div>';

		return div;
	}

	// Build initial Slots
	function buildSlots() {
		var startIndex = -options.minutesIntoFuture;
		var date_shift = moment.utc();
		date_shift.add(options.minutesIntoFuture, options.timeScale);

		for (var i = 0; i < totalSlotCount; i++) {
			date_shift = date_shift.subtract(1, options.timeScale);
			$('#all_list').append(divSlotAll(date_shift, 'all_item'));
		}

		// Set current time height
		var height = ($('#current_time')[0].scrollHeight * (options.minutesIntoFuture)) + options.currentTimePxOffset;
		$('#center').css({ top: height });

		$('.slotItem').show();

		//var popover = new bootstrap.Popover(document.querySelector('.popover-dismiss'), {
		//  container: 'body'
		//});
	}

	// function to build the popover content
	function buildPopoverContent(flight) {
		console.log(flight);
		var content = '';

		content += '';

		return content;
	}

	// Build "minutes into the future" Slot
	function addFutureSlot() {
		var allFirstSlotDate = moment.utc();
		allFirstSlotDate.add(options.minutesIntoFuture + 1, options.timeScale);
		$('#all_list').prepend(divSlotAll(allFirstSlotDate, 'all_item'));
		$('#all_item' + allFirstSlotDate.format('YYYYMMDDHHmm')).slideDown(options.timeStep, function(){});

	  	// If appending (scroll down), keep page at bottom.
	  	// window.scrollTo(0,document.body.scrollHeight);
	}

	// Build "minutes into the past" Slot
	function removePastSlot() {
		var allLastSlotDate = moment.utc();
		allLastSlotDate.subtract(options.minutesIntoPast, options.timeScale);
		$('#all_item' + allLastSlotDate.format('YYYYMMDDHHmm')).slideUp(options.timeStep, function(){ $(this).hide(options.timeStep, function(){ $(this).remove(); }); });

	  	// If appending (scroll down), keep page at bottom.
	  	// window.scrollTo(0,document.body.scrollHeight);
	}

	// Get flight data from FMS Engine
	function readFlightData() {
		$.ajax({
			method: "GET",
			//url: "{{ url('/xml') }}",
			url: "xml", 
			dataType: "json",
            		success: function(result) {
				if (result['Slot']) {
					var slotArray = result['Slot'];
					//console.log("yes");	
					// Check if one object or an array of object
					if (Array.isArray(slotArray)) {
						// More than 1 aircraft in the list
						
						for (let index = 0; index < slotArray.length; index++) {
							const element = slotArray[index];
							callsign = element['callsign'];
							
							if (callsign) {
								
								if (element['flightprocedure'] == 'auto') {
									// console.log("yes");
									// Use time to match div ID
									timestamp = element['eta'];
									formated_time = moment(timestamp, 'YYYY-MM-DDTHH:mm');
									feederFix_formatted = moment(element['feederFix'], 'YYYY-MM-DDTHH:mm');

									auto_id = 'callsign_auto_item' + formated_time.format('YYYYMMDDHHmm');
									auto_id_icon = 'callsign_auto_item_icon' + formated_time.format('YYYYMMDDHHmm');
									auto_id_ac_type = 'callsign_auto_item_ac_type' + formated_time.format('YYYYMMDDHHmm');
									auto_id_wtur = 'callsign_auto_item_wtur' + formated_time.format('YYYYMMDDHHmm');
									auto_id_regis = 'callsign_auto_item_regis' + formated_time.format('YYYYMMDDHHmm');
									auto_id_feeder_fix = 'callsign_auto_item_feeder_fix' + formated_time.format('YYYYMMDDHHmm');
									auto_right_click = 'auto_right_click' + formated_time.format('YYYYMMDDHHmm');

									$('#' + auto_id).html(callsign);
									$('#' + auto_id_icon).html("<i class='ps-3 fa fa-plane'></i>");
									$('#' + auto_id_ac_type).html(element['ac_type']);
									$('#' + auto_id_wtur).html(element['wtur']);
									$('#' + auto_id_regis).html(element['regis']);
									$('#' + auto_id_feeder_fix).html("&nbsp;&nbsp;" +feederFix_formatted.format('HH:mm'));
									$('#' + auto_right_click).removeClass("context-menu-two");
									$('#' + auto_right_click).addClass("context-menu-one");
									// Remove any other with same id
								} else if (element['flightprocedure'] == 'manual') {
									// Use time to match div ID
									timestamp = element['eta'];
									formated_time = moment(timestamp, 'YYYY-MM-DDTHH:mm');

									manual_id = 'callsign_manual_item' + formated_time.format('YYYYMMDDHHmm');
									manual_id_icon = 'callsign_manual_item_icon' + formated_time.format('YYYYMMDDHHmm');
									manual_id_ac_type = 'callsign_manual_item_ac_type' + formated_time.format('YYYYMMDDHHmm');
									manual_id_wtur = 'callsign_manual_item_wtur' + formated_time.format('YYYYMMDDHHmm');
									manual_id_regis = 'callsign_manual_item_regis' + formated_time.format('YYYYMMDDHHmm');
									// manual_right_click = 'manual_right_click' + formated_time.format('YYYYMMDDHHmm');

									$('#' + manual_id).html(callsign);
									$('#' + manual_id_icon).html("<i class='ps-3 fa fa-plane'></i>");
									$('#' + manual_id_ac_type).html(element['ac_type']);
									$('#' + manual_id_wtur).html(element['wtur']);
									$('#' + manual_id_regis).html(element['regis']);
									// $('#' + manual_right_click).addClass("context-menu-one");
									// Remove any other with same id
								}

								$('#btns_auto_item' + formated_time.format('YYYYMMDDHHmm')).show();
								$('#btns_manual_item' + formated_time.format('YYYYMMDDHHmm')).show();
							}
						}
					} else {
						// Only one aircraft in the list
						
						const element = slotArray;
						callsign = element['callsign'];

						if (callsign) {

							if (element['flightprocedure'] == 'auto') {
								// Use time to match div ID
								timestamp = element['eta'];
								formated_time = moment(timestamp, 'YYYY-MM-DDTHH:mm');
								feederFix_formatted = moment(element['feederFix'], 'YYYY-MM-DDTHH:mm');

								auto_id = 'callsign_auto_item' + formated_time.format('YYYYMMDDHHmm');
								auto_id_icon = 'callsign_auto_item_icon' + formated_time.format('YYYYMMDDHHmm');
								auto_id_ac_type = 'callsign_auto_item_ac_type' + formated_time.format('YYYYMMDDHHmm');
								auto_id_wtur = 'callsign_auto_item_wtur' + formated_time.format('YYYYMMDDHHmm');
								auto_id_regis = 'callsign_auto_item_regis' + formated_time.format('YYYYMMDDHHmm');
								auto_id_feeder_fix = 'callsign_auto_item_feeder_fix' + formated_time.format('YYYYMMDDHHmm');
								auto_right_click = 'auto_right_click' + formated_time.format('YYYYMMDDHHmm');

								$('#' + auto_id).html(callsign);
								$('#' + auto_id_icon).html("<i class='ps-3 fa fa-plane'></i>");
								$('#' + auto_id_ac_type).html(element['ac_type']);
								$('#' + auto_id_wtur).html(element['wtur']);
								$('#' + auto_id_regis).html(element['regis']);
								$('#' + auto_id_feeder_fix).html("&nbsp;&nbsp;" +feederFix_formatted.format('HH:mm'));
								$('#' + auto_right_click).removeClass("context-menu-two");
								$('#' + auto_right_click).addClass("context-menu-one");
								// Remove any other with same id
							} else if (element['flightprocedure'] == 'manual') {
								// Use time to match div ID
								timestamp = element['time'];
								formated_time = moment(timestamp, 'YYYY-MM-DDTHH:mm');

								manual_id = 'callsign_manual_item' + formated_time.format('YYYYMMDDHHmm');
								manual_id_icon = 'callsign_manual_item_icon' + formated_time.format('YYYYMMDDHHmm');
								manual_id_ac_type = 'callsign_manual_item_ac_type' + formated_time.format('YYYYMMDDHHmm');
								manual_id_wtur = 'callsign_manual_item_wtur' + formated_time.format('YYYYMMDDHHmm');
								manual_id_regis = 'callsign_manual_item_regis' + formated_time.format('YYYYMMDDHHmm');
								// manual_right_click = 'manual_right_click' + formated_time.format('YYYYMMDDHHmm');

								$('#' + manual_id).html(callsign);
								$('#' + manual_id_icon).html("<i class='ps-3 fa fa-plane'></i>");
								$('#' + manual_id_ac_type).html(element['ac_type']);
								$('#' + manual_id_wtur).html(element['wtur']);
								$('#' + manual_id_regis).html(element['regis']);
								// $('#' + manual_right_click).addClass("context-menu-one");
								// Remove any other with same id
							}

							$('#btns_auto_item' + formated_time.format('YYYYMMDDHHmm')).show();
							$('#btns_manual_item' + formated_time.format('YYYYMMDDHHmm')).show();
						}
					}
				}
            		}
		});
	}

	// Function to read Reserved List XML and indicate on timeline.
	function readReservedTimeSlotDate() {
		$.ajax({
			method: "GET",
			//url: "{{ url('/xml/reservedtimeslot') }}", 
			url: "xml/reservedtimeslot",
			dataType: "json",
            success: function(result) {
				// Do stuff.
            }
		});
	}

	// Function to refresh the page if idle 
	function setIdle(cb, seconds) {
	    var timer; 
	    var interval = seconds * 1000;
	    function refresh() {
            clearInterval(timer);
            timer = setTimeout(cb, interval);
	    };
	    $(document).on('keypress click', refresh);
	    refresh();
	}

	init();

</script>
@endsection
