@extends('layouts.app')

@section('content')
<div class="container">
    <div class="row">
    	<div class="d-flex justify-content-start pt-3">
    		<h4>Configurations for Flow Control</h4>
    	</div>
    	<hr class="mt-3 mb-5">
        <div class="d-flex justify-content-start w-75">
            <form method="post" action="configurations/save">
				@csrf
              	<div class="mb-3">
	                <label for="landingrate" class="form-label">Landing rate</label>
					@error('landingrate')
						<div class="alert alert-danger">{{ $message }}</div>
					@enderror
	                <input type="number" class="form-control" id="landingrate" name="landingrate" aria-describedby="LandingRate"  value="{{ $landingrate }}">
	                <div id="LandingRate" class="form-text">Landing rate of aircraft</div>
              	</div>
              	<div class="mb-3">
	                <label for="stablesequenceperiod" class="form-label">Stable Sequence period</label>
					@error('ssperiod')
						<div class="alert alert-danger">{{ $message }}</div>
					@enderror
	                <input type="number" class="form-control" id="stablesequenceperiod" name="stablesequenceperiod" aria-describedby="StableSequencePeriod"  value="{{ $ssperiod }}">
	                <div id="StableSequencePeriod" class="form-text">Stable sequence period of flights that are locked.</div>
              	</div>
              	<label for="defaultflowcontrolposition" class="form-label">Default flow control position</label>
				@error('defaultflowcontrolposition')
					<div class="alert alert-danger">{{ $message }}</div>
				@enderror
              	<select id="defaultflowcontrolposition" name="defaultflowcontrolposition" class="form-select" aria-label="Default flow control position">
					
				  	@for ($i = 0; $i <= 14; $i++)
						@if ($defaultflowcontrolposition == $i)
							<option value="{{ $i }}" selected>CWP{{ $i }}</option>
						@else
							<option value="{{ $i }}">CWP{{ $i }}</option>
						@endif
					@endfor

	              	
              	</select>
              	<div class="form-text mb-3">Position on OPS that will be in charge of the flow management.</div>
              	<label for="runway" class="form-label">Runway</label>
				@error('rwy')
					<div class="alert alert-danger">{{ $message }}</div>
				@enderror
              	<select id="runway" name="runway" class="form-select" aria-label="Runway">

						@if ($rwy == 32)
							<option value="32" selected>32</option>
							<option value="14">14</option>
						@else
							<option value="32">32</option>
							<option value="14" selected>14</option>
						@endif

              	</select>
              	<div class="form-text mb-3">Runway being used right now.</div>
              	<hr class="mb-4 mt-4"> 
	            <!-- <div class="form-check mb-5">
	              <input class="form-check-input" type="checkbox" value="" id="darkmode" name="darkmode">
	              <label class="form-check-label" for="darkmode">
	                Theme Dark mode
	              </label>
	            </div> -->
              	<button type="submit" class="btn btn-primary">Save</button>
            </form>
        </div>
    </div>
</div>
@endsection
