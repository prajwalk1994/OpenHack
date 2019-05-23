import React, { Component } from 'react';
import Axios from 'axios';
import url from '../../config/config';


class ResultsReport extends Component {
    constructor(props) {
        super(props);
        this.state = {
            resultsList: ["Hack 1", "hack 2"],
            selectedHackathonResult: [],
            selectedId: "",
        }
    }

    componentDidMount = () => {
        //get all finalized hackathons
        Axios.get(url + "/finalHackathons")
            .then((response) => {
                console.log("all final hackathons", response.data);
                this.setState({
                    resultsList: response.data,
                })
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
                this.setState({
                    selectedHackathonResult: response.data,
                    selectedId: index,
                })
            })
            .catch((err) => {
                if (err.response) {
                    alert(err.response.data);
                }
            })
    }

    render() {
        var resultsList;
        resultsList = this.state.resultsList.map((item) => {
            return (
                <div className="row">
                    <li class="list-group-item col-sm-8 mr-2" >{item.name}</li>
                    <button className="btn btn-dark col-sm-3" onClick={(e) => this.handleHackathonResults(e, item.id)}>view results</button>
                </div>
            )
        })

        var selectedHackathonDiv;
        selectedHackathonDiv = this.state.selectedHackathonResult.map((item, index) => {
            if(index<3){
                var color = "rgb(252, 214, 112,0.7)";
            }else{
                var color = "white"
            }
            return (
                <div className="row">
                    <li className="list-group-item col-sm-6" style={{backgroundColor:color}}>
                        {item.teamId.teamName}
                    </li>
                    <li className="list-group-item col-sm-2 mt-2 ml-5">{item.grade}</li>
                </div>
            )
        })

        return (
            <div className="container mt-5">
                <h1 style={{ color: "black" }}>Finalized Hackthons Results</h1>
                <div className="row formContainer">
                    <ul className="col-sm-6 list-group">{resultsList}</ul>
                    <ul className="col-sm-6 list-group">
                        <div className="row" style={{ marginBottom:"-30px"}}>
                            <label className="col-sm-6">
                                Team Name
                            </label>
                            <label className="col-sm-2 mt-2 ml-5">Score</label>
                        </div>
                        {this.state.selectedId ? selectedHackathonDiv : <div className="col-sm-6"></div>}
                    </ul>
                </div>
            </div>
        );
    }
}

export default ResultsReport;