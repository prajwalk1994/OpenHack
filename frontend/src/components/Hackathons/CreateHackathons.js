import React, { Component } from 'react';

class CreateHackathons extends Component {

    constructor(props) {
        super(props);
        this.state = {

        }
    }

    handleSubmit = (e) => {
        e.preventDefault();
    }
    render() {
        return (
            <div>
                <div className="container mt-5">
                    <h3>Create Hackathon</h3>
                    <center>
                        <div className="row justify-content-center">
                            <input className="form-control col-sm-4" type="text" name="name" placeholder="Event Name"></input>
                        </div>
                        <div className="row justify-content-center">
                            <label className="col-sm-2">Start Date</label>
                            <input className="form-control col-sm-2" type="date" name="startDate" placeholder="Start Date"></input>
                        </div>
                        <div className="row justify-content-center">
                            <label className="col-sm-2">End Date</label>
                            <input className="form-control col-sm-2" type="date" name="endDate" placeholder="End Date"></input>
                        </div>
                        <div className="row justify-content-center">
                            <input className="form-control col-sm-4" type="textArea" name="fee" placeholder="Registration fee"></input>
                        </div>
                        <div className="row justify-content-center">
                            <input className="form-control col-sm-4" type="text" name="judges" placeholder="Judges"></input>
                        </div>
                        <div className="row justify-content-center">
                            <input className="form-control col-sm-4" type="text" name="minTeam" placeholder="Min Team Size"></input>
                        </div>
                        <div className="row justify-content-center">
                            <input className="form-control col-sm-4" type="text" name="maxTeam" placeholder="Max Team Size"></input>
                        </div>
                        <div className="row justify-content-center">
                            <input className="form-control col-sm-4" type="text" name="sponsers" placeholder="Sponsers"></input>
                        </div>
                        <div className="row justify-content-center">
                            <input className="form-control col-sm-4" type="text" name="sponserDiscount" placeholder="Sponser Discount"></input>
                        </div>
                        <div className="row justify-content-center">
                            <button className="btn btn-primary" name="pay" onClick={this.handleSubmit}>Submit</button>
                        </div>
                    </center>
                </div>
            </div>
        );
    }
}

export default CreateHackathons;