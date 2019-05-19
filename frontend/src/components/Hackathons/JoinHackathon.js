import React, { Component } from 'react';

class JoinHackathon extends Component {
    constructor(props) {
        super(props);
        this.state = {
            minteamSize: 5,
            maxteamSize: 10
        }
    }
    render() {

        var teamMembers = Array.apply(null, { length: this.state.minteamSize }).map((e, i) => (
            <tr >
                <td><input className="form-control mt-2" type="text" name={"member" + (i + 1)} placeholder={"team member " + (i + 1)} ></input></td>
                <td>
                    <select className="form-control mt-2" id="role">
                        <option>ProductManger</option>
                        <option>Engineer</option>
                        <option>Full Stack</option>
                        <option>Designer</option>
                        <option>Other</option>
                    </select>
                </td>
            </tr>
        ));


        return (
            <div className="container mt-5">
                <div className="formContainer" style={{marginLeft:"25%",marginRight:"25%"}}>
                    <center>
                        <h3>Enter Team Details</h3>
                        <table>
                            <tr>
                                <td><input className="form-control mt-1" type="text" name="teamName" placeholder="Team Name" />
                                </td>
                                <td>
                                </td>
                            </tr>
                            {teamMembers}
                        </table>
                        <div>
                            <button className="form_element btn btn_login">Join Hackathon</button>
                        </div>
                    </center>
                </div>
            </div>
        );
    }
}

export default JoinHackathon;