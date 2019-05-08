import React, { Component } from 'react';

class submission extends Component {
    constructor(props) {
        super(props);
        this.state = {
            url: "",
        }
    }

    doSubmission=(e)=>{
        e.preventDefault();
        axios.get(url + "/submission")
            .then((response) => {
                console.log(response.data)
                
                console.log("state after response",this.state)
            })
            .catch((err) => {
                if(err.response){
                console.log("errror", err.response)
                }else{
                    alert("something went wrong")
                }
            })
    }
    render() {
        return (
            <div className="container">
                <h3>Hackathon Submission</h3>
                <center>
                    <div className="row justify-content-center">
                        <label className="col-sm-2">CODE URL</label>
                        <input className="form-control col-sm-4" type="text" name="url" value={this.state.url}></input>
                    </div>
                    <div className="row justify-content-center">
                        <button className="btn btn-primary" name="pay" onClick={this.doSubmission}>Submit Code URL</button>
                    </div>
                </center>
            </div>
        );
    }
}

export default submission;