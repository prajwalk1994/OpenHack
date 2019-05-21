import React, { Component } from 'react';
import Axios from 'axios';
import url from '../../config/config';


class ResultsReport extends Component {
    constructor(props) {
        super(props);
        this.state = {
            resultsList: ["Hack 1", "hack 2"],
            selectedHackathonResult: [],
            selectedId: ""
        }
    }

    componentDidMount = () => {
        //get all finalized hackathons
        Axios.get(url + "/finalHackathons")
            .then((response) => {
                console.log("all final hackathons", response.data);
            })
            .catch((err) => {
                if (err.response) {
                    alert(err.response.data);
                }
            })
    }

    handleHackathonResults = (e, index) => {
        e.preventDefault();
        //Axios Call to get hackathon results of that particular index.
        Axios.get(url + "/teamsByHackathon/" + index)
            .then((response) => {
                console.log("selected hackathon results", response.data);
            })
            .catch((err) => {
                if (err.response) {
                    alert(err.response.data);
                }
            })
    }

    render() {
        var resultsList = ""
        resultsList = this.state.resultsList.map((item, index) => {
            return (
                <div className="row">
                    <li class="list-group-item col-sm-8 mr-2" >{item}</li>
                    <button className="btn btn-dark col-sm-3" onClick={(e) => this.handleHackathonResults(e, index)}>view results</button>
                </div>
            )
        })

        var selectedHackathonDiv = "";
        selectedHackathonDiv = this.state.selectedHackathonResult.map((item, index) => {
            return(
                <div>
                    {item}
                </div>
            )
        })

        return (
            <div className="container mt-5">
                <h1 style={{ color: "black" }}>Finalized Hackthons Results</h1>
                <div className="row formContainer">
                    <ul className="col-sm-6 list-group">{resultsList}</ul>
                    {this.state.selectedId ? <div className="col-sm-6"> Hackathon1 results</div> : <div className="col-sm-6"></div>}
                </div>
            </div>
        );
    }
}

export default ResultsReport;