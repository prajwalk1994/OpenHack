import React, { Component } from 'react';
import Axios from 'axios';
import url from '../../config/config';
import { Link, Redirect } from "react-router-dom";

class CreateHackathons extends Component {

    constructor(props) {
        super(props);
        this.state = {
            email: localStorage.getItem("email"),
            name: "",
            description: "",
            endDate: "",
            judgeList: [],
            maxTeam: 0,
            minTeam: 0,
            name: "",
            regFee: "",
            sponDiscount: "",
            sponsers: [],
            startDate: ""
        }
    }

    handleChange = (e) => {
        this.setState({
            [e.target.name]: e.target.value
        })
    }

    handleSubmit = async (e) => {
        e.preventDefault();
        // var judges=await this.state.judgeList.split(",")
        // var judgeList=[]
        // for (let i of judges){
        //     await judgeList.push({email:i})
        // }
        // console.log(judgeList)
        
        if(this.state.judgeList.length==0){
            alert("Judge_list should not be empty")
            return
        }

        var hackathon = await {
            ...this.state,
            judgeList: await this.state.judgeList.split(",").map(function (item) { return item.trim() }),
        }
        
        
        if(this.state.sponsers.length!=0){
            hackathon=await {
                ...hackathon,
                sponsers: await this.state.sponsers.split(",").map(function (item) { return item.trim() })
            }
        }
        // console.log(this.state)
        console.log(hackathon)

        //Dates check
        console.log(new Date().getTime(), (new Date(this.state.startDate).getTime()), (new Date(this.state.endDate).getTime()))

        if (this.state.name.length == 0) {
            alert("Name required")
        }
        else {
            if (new Date().getTime() < (new Date(this.state.startDate).getTime()) && (new Date(this.state.startDate).getTime()) < new Date(this.state.endDate).getTime()) {
                if (this.state.minTeam < this.state.maxTeam) {
                    Axios.post(url + `/hackathon?email=${this.state.email}`, hackathon).then(async (res) => {
                        console.log(res)
                        await this.setState({
                            redirectTo: <Redirect to="/searchHackathons" />
                        })
                    }).catch(err => {
                        if (err.response) {
                            alert(err.response.data)
                            console.log(err)
                        }
                        else {
                            alert("Something went wrong!")
                        }
                    })
                } else {
                    alert("invalid team size")
                }

            } else {
                alert("invalid time")
            }
        }



    }


    render() {
        return (
            <div>
                <div>
                    {this.state.redirectTo}
                </div>
                <div className="container mt-5">
                    <div className="formContainer" style={{ marginLeft: "25%", marginRight: "25%" }}>
                        <h3>Create Hackathon</h3>
                        <center>
                            <div className="row justify-content-center">
                                <input className="form-control col-sm-8" onChange={this.handleChange.bind(this)} type="text" name="name" placeholder="Event Name"></input>
                            </div>
                            <div className="row justify-content-center">
                                <label className="col-sm-4">Start Date</label>
                                <input className="form-control col-sm-4" onChange={this.handleChange.bind(this)} type="date" name="startDate" placeholder="Start Date"></input>
                            </div>
                            <div className="row justify-content-center">
                                <label className="col-sm-4">End Date</label>
                                <input className="form-control col-sm-4" onChange={this.handleChange.bind(this)} type="date" name="endDate" placeholder="End Date"></input>
                            </div>
                            <div className="row justify-content-center">
                                <input className="form-control col-sm-8" onChange={this.handleChange.bind(this)} type="textArea" name="description" placeholder="Description"></input>
                            </div>
                            <div className="row justify-content-center">
                                <input className="form-control col-sm-8" onChange={this.handleChange.bind(this)} type="number" name="regFee" placeholder="Registration fee"></input>
                            </div>
                            <div className="row justify-content-center">
                                <input className="form-control col-sm-8" onChange={this.handleChange.bind(this)} type="text" name="judgeList" placeholder="Judges"></input>
                            </div>
                            <div className="row justify-content-center">
                                <input className="form-control col-sm-8" onChange={this.handleChange.bind(this)} type="number" name="minTeam" placeholder="Min Team Size"></input>
                            </div>
                            <div className="row justify-content-center">
                                <input className="form-control col-sm-8" onChange={this.handleChange.bind(this)} type="number" name="maxTeam" placeholder="Max Team Size"></input>
                            </div>
                            <div className="row justify-content-center">
                                <input className="form-control col-sm-8" onChange={this.handleChange.bind(this)} type="text" name="sponsers" placeholder="Sponsers"></input>
                            </div>
                            <div className="row justify-content-center">
                                <input className="form-control col-sm-8" onChange={this.handleChange.bind(this)} type="number" name="sponDiscount" placeholder="Sponser Discount"></input>
                            </div>
                            <div className="row justify-content-center">
                                <button className="btn btn_login" name="pay" onClick={this.handleSubmit}>Submit</button>
                            </div>
                        </center>
                    </div>
                </div>
            </div>
        );
    }
}

export default CreateHackathons;