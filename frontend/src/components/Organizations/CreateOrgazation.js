import React, { Component } from 'react';

class CreateOrgazation extends Component {
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
                    <h3>Create Organization</h3>
                    <center>
                        <div className="row justify-content-center">
                            <input className="form-control col-sm-4" type="text" name="name" placeholder="Orgnization Name"></input>
                        </div>
                        <div className="row justify-content-center">
                            <input className="form-control col-sm-4" type="text" name="name" placeholder="Orgnization Owner"></input>
                        </div>
                        <div className="row justify-content-center">
                            <input className="form-control col-sm-4" type="textArea" name="name" placeholder="Orgnization Description"></input>
                        </div>
                        <div className="row justify-content-center">
                            <input className="form-control col-sm-4" type="text" name="name" placeholder="Orgnization Address"></input>
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

export default CreateOrgazation;